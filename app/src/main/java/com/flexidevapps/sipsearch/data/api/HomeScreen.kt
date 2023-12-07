package com.flexidevapps.sipsearch.data.api

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flexidevapps.sipsearch.R
import com.flexidevapps.sipsearch.viewmodels.SipSearchViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SipSearch(viewModel: SipSearchViewModel) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var searchText by remember { mutableStateOf("") }
    val cocktailsRequestState by viewModel.cocktailsState
    var job: Job? = null

    Box(modifier = Modifier
        .fillMaxSize()
        .paint(
            painterResource(id = R.drawable.main_background_image),
            contentScale = ContentScale.FillBounds
        )
        .clickable {
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
            Spacer(Modifier.size(30.dp))
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

            Spacer(Modifier.size(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                OutlinedDropdownCompose(
                    "Search By"
                ) {

                    // Implement what to do when the button is clicked.
                }
                Spacer(modifier = Modifier.size(5.dp))
                OutlinedTextFieldCompose(
                    searchText,
                    {
                        searchText = it
                        job?.cancel()
                        job = MainScope().launch {
                            delay(500L)
                            if (it.toString().isNotEmpty()){
                                viewModel.getCocktailByName(it)
                            }
                        }

                    },
                    Icons.Outlined.Search,
                    {
                        viewModel.getCocktailByName(searchText)

                    },
                    "Search Text"
                )
            }

            Spacer(modifier = Modifier.size(15.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Buttons("Filter") {
                    //Implement Filter Button action
                }
                Buttons("Show All") {
                    // //Implement Show All Button action
                }
                Buttons("Random") {
                    // //Implement Show All Button action
                }

            }

            Spacer(modifier = Modifier.size(15.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                val alphabetList = listOf(
                    "A",
                    "B",
                    "C",
                    "D",
                    "E",
                    "F",
                    "G",
                    "H",
                    "I",
                    "J",
                    "K",
                    "L",
                    "M",
                    "N",
                    "O",
                    "P",
                    "Q",
                    "R",
                    "S",
                    "T",
                    "U",
                    "V",
                    "W",
                    "X",
                    "Y",
                    "Z"
                )


                items(alphabetList) { it ->
                    Text(
                        modifier = Modifier
                            .clickable {
                                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                            },
                        text = it,
                        color = Color.White


                    )
                }

            }
            Spacer(modifier = Modifier.size(15.dp))



            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(1.dp, Color.White, shape = RoundedCornerShape(8.dp))
                    .background(colorResource(id = R.color.BackgroundGreyTheme))
            )
            {
                when {
                    cocktailsRequestState.loading -> {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                    cocktailsRequestState.error != null ->{
                        Text(text = cocktailsRequestState.error!!)
                    }
                    else ->{
                        VerticalLazyGrid(drink = cocktailsRequestState.cocktailList)

                    }

            }

            }
        }
    }

}

@Composable
fun VerticalLazyGrid(drink: List<Drink> ) {
    LazyVerticalGrid(GridCells.Fixed(2), Modifier.fillMaxSize()) {
        items(drink){
            Text(text = it.strDrink)
        }
    }
    
}


@Composable
fun Buttons(
    text:String,
    onClick: () -> Unit)
{
    OutlinedButton(
        modifier = Modifier
            .height(40.dp)
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
    text:String,
    onClick: () -> Unit)
{
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
        Text(text,
            modifier = Modifier
                .padding(0.dp, 0.dp, 0.dp, 0.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal
        )
    }
}


@Composable
fun OutlinedTextFieldCompose(
    value:String,
    onValueChange: (it:String) -> Unit,
    trailingIcon: ImageVector,
    trailingIconClickListener:() -> Unit,
    labelText:String
) {

    val context = LocalContext.current
    var isFocused by remember { mutableStateOf(false)}


    OutlinedTextField(
        modifier = Modifier
            .height(55.dp)
            .onFocusChanged {
                isFocused = it.isFocused
            },
        value = value,
        onValueChange = onValueChange,
        textStyle = androidx.compose.material3.LocalTextStyle.current.copy(
            fontSize = 12.sp
        ),
        maxLines = 1,
        label = {
            Box(
                modifier = Modifier
                    .width(70.dp)
                    .wrapContentWidth(Alignment.Start)
                    .background(Color.Transparent)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(R.color.GreyTheme))
                        .padding(
                            start = if (isFocused) 0.dp else 0.dp,
                            top = if (isFocused) 0.dp else 0.dp,
                            end = if (isFocused) 0.dp else 0.dp,
                            bottom = if (isFocused) 0.dp else 9.dp
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