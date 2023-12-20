package com.flexidevapps.sipsearch.ui.homepage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.flexidevapps.sipsearch.R
import com.flexidevapps.sipsearch.data.Drink
import com.flexidevapps.sipsearch.viewmodels.HomeScreenViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    navigationToDetailsScreen: (drink:Drink) -> Unit
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager  = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    var selectedLetter by remember {mutableStateOf("")}
    var flagSearchBy by remember { mutableStateOf(false) }
    val vmCocktailList = viewModel.cocktailsState.value.cocktailList
    val cocktailsRequestState by viewModel.cocktailsState
    val searchText by viewModel.searchText
    val searchBy by viewModel.searchBy
    val alphabetList = remember { AlphabetList().alphabetList}

    var job: Job? = null

    LaunchedEffect(Unit) {
        if (searchBy == "Name") {
            viewModel.getCocktailByName(searchText.ifBlank { "Margarita" })
        } else {
            viewModel.searchCocktailIngredientName(searchText.ifBlank { "Lemon" })
        }
    }


    Box(modifier = Modifier
        .fillMaxSize()
        .paint(
            painterResource(id = R.drawable.main_background_image),
            contentScale = ContentScale.FillBounds
        )
        .clickable(
            indication = null,
            interactionSource = interactionSource
        ) {
            keyboardController?.hide()
            focusManager.clearFocus(true)

        }
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(Modifier.size(20.dp))
            Row {
                Text(
                    text = "SipSearch",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Medium,
                )
                Spacer(Modifier.size(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.cocktail_shacker),
                    contentDescription = "cocktail mixer",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(35.dp)
                )
            }

            Spacer(Modifier.size(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,

            ) {
                OutlinedDropdownCompose(
                    text = searchBy,
                    onClick = {
                        flagSearchBy = !flagSearchBy
                        keyboardController?.hide()
                        focusManager.clearFocus(true)
                        selectedLetter = ""
                    },
                    nameOnClick = {
                        flagSearchBy = false
                        viewModel.changeSearchByInput("Name")
                        keyboardController?.hide()
                        focusManager.clearFocus(true)
                        viewModel.changeSearchInput("")
                        selectedLetter = ""
                    },
                    ingredientOnClick = {
                        flagSearchBy = false
                        viewModel.changeSearchByInput("Ingredient")
                        keyboardController?.hide()
                        focusManager.clearFocus(true)
                        viewModel.changeSearchInput("")
                        selectedLetter = ""
                    },
                    onDismissRequest = {
                        flagSearchBy = false
                    },
                    flagSearchBy = flagSearchBy
                )
                Spacer(modifier = Modifier.size(5.dp))
                OutlinedTextFieldCompose(
                    viewModel = viewModel,
                    value = searchText,
                    onValueChange =  {
                        viewModel.changeSearchInput(it)
                        job?.cancel()

                        when(viewModel.searchBy.value) {
                            "Name" -> {
                                job = MainScope().launch {
                                    delay(500L)
                                    viewModel.getCocktailByName(it)
                                }
                            }
                            "Ingredient" -> {
                                job = MainScope().launch {
                                    delay(500L)
                                    viewModel.searchCocktailIngredientName(it)
                                }
                            }
                        }
                        selectedLetter = ""

                    },
                    Icons.Default.Clear,
                    {
                        if(vmCocktailList.isNotEmpty() && viewModel.searchText.value.isNotEmpty()) {
                            viewModel.changeSearchInput("")
                            viewModel.clearCocktailList()
                            viewModel.getCocktailByName(viewModel.searchText.value)
                            }

                        keyboardController?.hide()
                        focusManager.clearFocus(true)
                        selectedLetter = ""
                    },
                    "Search"
                )
            }

            Spacer(modifier = Modifier.size(15.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Buttons("Random") {
                    keyboardController?.hide()
                    focusManager.clearFocus(true)
                    viewModel.changeSearchInput("")
                    viewModel.getRandomCocktail()
                    selectedLetter = ""


                }

            }

            Spacer(modifier = Modifier.size(15.dp))


            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                items(alphabetList) { letter ->
                    Text(
                        modifier = Modifier
                            .clickable {
                                selectedLetter = letter
                                viewModel.searchCocktailLetter(letter)
                                keyboardController?.hide()
                                focusManager.clearFocus(true)
                            }
                            .background(color = if (letter == selectedLetter) colorResource(id = R.color.OrangeTheme) else Color.Transparent),
                        text = letter,
                        color = Color.White


                    )
                }

            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(1.dp, Color.White, shape = RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
                    .background(colorResource(id = R.color.BackgroundGreyTheme))
            )
            {


                when {
                    cocktailsRequestState.loading -> {
                        if(viewModel.searchText.value.isEmpty()){
                            viewModel.clearCocktailList()
                        } else {
                            CircularProgressIndicator(Modifier.align(Alignment.Center))
                        }
                    }
                    cocktailsRequestState.error != null ->{
                        Text(
                            text = cocktailsRequestState.error!!,
                            Modifier.align(Alignment.Center),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    else ->{
                        LazyVerticalGrid(
                            GridCells.Fixed(2),
                            Modifier
                                .fillMaxSize(),
                            contentPadding = PaddingValues(5.dp)
                        ) {
                            items(
                                items = vmCocktailList,
                                key = { it.idDrink }
                            ) {drink ->
                                VerticalLazyGrid(
                                    drink,
                                ) {
                                    navigationToDetailsScreen(drink)
                                    }

                            }
                        }

                    }
                 }
            }
        }
    }

}

@Composable
fun VerticalLazyGrid(
    drink: Drink,
    navigationToDetailsScreen:() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .clip(RoundedCornerShape(5))
            .background(colorResource(id = R.color.GreyTheme))
            .clickable {
                navigationToDetailsScreen()
            },
    ){
        Image(
            painter = rememberAsyncImagePainter(drink.strDrinkThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .border(1.dp, colorResource(id = R.color.black), shape = RoundedCornerShape(5))
                .clip(RoundedCornerShape(5)),
            )

        Text(text = drink.strDrink,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(5.dp, 0.dp, 5.dp, 5.dp),
            color = Color.White,
            textAlign = TextAlign.Center

        )

    }

}

@Composable
fun Buttons(
    text:String,
    onClick: () -> Unit,
)
{
    OutlinedButton(
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 0.dp),
        onClick = onClick,
        border = BorderStroke(1.dp, Color.White),
        shape = RoundedCornerShape(10),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = colorResource(id = R.color.white),
            containerColor = colorResource(id = R.color.OrangeTheme)
        ),
    ) {
        androidx.compose.material3.Text(text, modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 0.dp),
        )
    }
}

@Composable
fun OutlinedDropdownCompose(
    text: String,
    onClick: () -> Unit,
    nameOnClick:() -> Unit,
    ingredientOnClick:() -> Unit,
    onDismissRequest:() -> Unit,
    flagSearchBy: Boolean
)
{
    Box() {
        OutlinedButton(
            modifier = Modifier
                .padding(0.dp, 7.dp, 0.dp, 0.dp)
                .height(47.dp),
            onClick = onClick,
            border = BorderStroke(1.dp, Color.White),
            shape = RoundedCornerShape(8),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = colorResource(id = R.color.white),
                containerColor = colorResource(id = R.color.GreyTheme)
            ),
            ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(0.dp, 4.dp, 0.dp, 0.dp)
                    .wrapContentWidth(),
            ) {
                Text(
                    text = text,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    )
                Spacer(modifier = Modifier.size(0.dp))
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.CenterVertically)
                )

            }

        }
        DropdownMenu(expanded = flagSearchBy , onDismissRequest = { onDismissRequest.invoke() })
        {
            DropdownMenuItem(text = { Text(text = "Name") }, onClick = { nameOnClick.invoke()  })
            DropdownMenuItem(text = { Text(text = "Ingredient") }, onClick = { ingredientOnClick.invoke()})
            
        }
    }
}


@Composable
fun OutlinedTextFieldCompose(
    viewModel: HomeScreenViewModel,
    value:String,
    onValueChange:(it:String) -> Unit,
    trailingIcon: ImageVector,
    trailingIconClickListener:() -> Unit,
    labelText:String
) {

    var isFocused by remember { mutableStateOf(false)}
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .height(55.dp)
            .onFocusChanged {
                isFocused = it.isFocused
            },
        value = value,
        onValueChange = onValueChange,
        keyboardActions = KeyboardActions {
            focusManager.clearFocus(true)
        },
        textStyle = androidx.compose.material3.LocalTextStyle.current.copy(
            fontSize = 12.sp
        ),
        maxLines = 1,
        label = {
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentWidth(Alignment.Start)
                    .background(Color.Transparent)

            ) {
                Text(
                    modifier = Modifier
                        .wrapContentWidth()
                        .background(Color.Transparent)
                        .padding(
                            start = 0.dp,
                            top = 0.dp,
                            end = 0.dp,
                            bottom = if (isFocused || viewModel.searchText.value.isNotBlank()) 0.dp else 9.dp
                        ),
                    text = labelText,
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
        },
        trailingIcon = {
            Icon(trailingIcon,
                contentDescription = null,
                Modifier.
                clickable {
                    trailingIconClickListener.invoke()
                }

            )
        },

        colors = OutlinedTextFieldDefaults.colors (
            //Boc
            focusedContainerColor = colorResource(id = R.color.GreyTheme),
            unfocusedContainerColor = colorResource(id = R.color.GreyTheme),
//        //Borders
            focusedBorderColor = colorResource(id = R.color.white),
            unfocusedBorderColor = colorResource(id = R.color.white),
//        //Text
            focusedTextColor = colorResource(id = R.color.white),
            unfocusedTextColor = colorResource(id = R.color.white)
        ),


    )
}
