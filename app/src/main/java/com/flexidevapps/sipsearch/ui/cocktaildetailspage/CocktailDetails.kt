package com.flexidevapps.sipsearch.ui.cocktaildetailspage

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.flexidevapps.sipsearch.R
import com.flexidevapps.sipsearch.data.Drink
import com.flexidevapps.sipsearch.viewmodels.CocktailDetailsScreenViewModel


@Composable
fun CocktailDetailsScreen(
    viewModel: CocktailDetailsScreenViewModel,
    drink:Drink,
) {
    val cocktailsRequestState by viewModel.cocktailsState
    val context = LocalContext.current


    Box(modifier = Modifier
        .fillMaxSize()) {
        ShowCocktailDetails(drink)
    }
}

@Composable
fun ShowCocktailDetails(drink: Drink) {
    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = drink.strDrink,
            Modifier
                .padding(10.dp, 10.dp, 10.dp, 0.dp),
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium,
        )
        Spacer(Modifier.size(10.dp))

        Column(
            Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(5))
                .border(1.dp, Color.White, shape = RoundedCornerShape(5))
                .background(colorResource(id = R.color.BackgroundGreyTheme))
                .padding(10.dp)

        ) {
            Row() {
                Column(Modifier.width(190.dp)) {
                    Text(
                        text = "Glass",
                        Modifier
                            .padding(10.dp, 0.dp, 0.dp, 0.dp),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Text(
                        text = drink.strGlass,
                        Modifier
                            .padding(10.dp, 0.dp, 0.dp, 0.dp),
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                    )
                    Text(
                        text = "Ingredients",
                        Modifier
                            .padding(10.dp, 15.dp, 0.dp, 0.dp),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Column(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(10.dp, 0.dp, 0.dp, 0.dp),
                    ) {
                        val ingredientList = cocktailIngredientsList(drink)
                        for (i in ingredientList.indices) {
                            Text(
                                text = ingredientList[i],
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }

                Spacer(Modifier.size(10.dp))
                Column(
                    Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(drink.strDrinkThumb),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .border(
                                2.dp,
                                colorResource(id = R.color.black),
                                shape = RoundedCornerShape(5)
                            )
                            .clip(RoundedCornerShape(5)),
                    )
                    Spacer(Modifier.size(5.dp))
                    if (drink.strVideo != null) {
                        // Video tutorial btn
                        OutlinedButton(
                            onClick = {
                                Toast.makeText(context, drink.strVideo, Toast.LENGTH_SHORT)
                                    .show()
                            },
                            Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 0.dp, 0.dp, 0.dp),
                            border = BorderStroke(1.dp, Color.White),
                            shape = RoundedCornerShape(10),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = colorResource(id = R.color.white),
                                containerColor = colorResource(id = R.color.OrangeTheme)
                            )
                        ) {
                            Text(
                                text = "Tutorial Video",
                                Modifier
                                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
                Spacer(Modifier.size(10.dp))
                Text(
                    text = "Instructions",
                    Modifier
                        .padding(10.dp, 0.dp, 0.dp, 0.dp),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                )

                Text(
                    text = drink.strInstructions,
                    Modifier
                        .padding(10.dp, 0.dp, 0.dp, 0.dp),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
                Spacer(Modifier.size(10.dp))
                Text(
                    text = "Category",
                    Modifier
                        .padding(10.dp, 0.dp, 0.dp, 0.dp),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = drink.strCategory,
                    Modifier
                        .padding(10.dp, 0.dp, 0.dp, 0.dp),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                )
                Spacer(Modifier.size(10.dp))
                Text(
                    text = "Alcoholic",
                    Modifier
                        .padding(10.dp, 0.dp, 0.dp, 0.dp),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = drink.strAlcoholic,
                    Modifier
                        .padding(10.dp, 0.dp, 0.dp, 0.dp),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    }

private fun cocktailIngredientsList(drink: Drink): MutableList<String> {
    val ingredientList = mutableListOf<String>()

    fun addIngredient(ingredient: String?, measure: String?) {
        if (ingredient != null) {
            ingredientList.add("• ${if (measure != null) "$measure of " else ""}$ingredient")
        }
    }

    addIngredient(drink.strIngredient1, drink.strMeasure1)
    addIngredient(drink.strIngredient2, drink.strMeasure2)
    addIngredient(drink.strIngredient3, drink.strMeasure3)
    addIngredient(drink.strIngredient4, drink.strMeasure4)
    addIngredient(drink.strIngredient5, drink.strMeasure5)
    addIngredient(drink.strIngredient6, drink.strMeasure6)
    addIngredient(drink.strIngredient7, drink.strMeasure7)
    addIngredient(drink.strIngredient8, drink.strMeasure8)
    addIngredient(drink.strIngredient9, drink.strMeasure9)
    addIngredient(drink.strIngredient10, drink.strMeasure10)
    addIngredient(drink.strIngredient11, drink.strMeasure11)
    addIngredient(drink.strIngredient12, drink.strMeasure12)
    addIngredient(drink.strIngredient13, drink.strMeasure13)
    addIngredient(drink.strIngredient14, drink.strMeasure14)
    addIngredient(drink.strIngredient15, drink.strMeasure15)

    return ingredientList
}