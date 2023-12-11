package com.flexidevapps.sipsearch.ui.cocktaildetailspage

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.flexidevapps.sipsearch.R
import com.flexidevapps.sipsearch.data.Drink
import com.flexidevapps.sipsearch.viewmodels.CocktailDetailsScreenViewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun CocktailDetailsScreen(
    viewModel: CocktailDetailsScreenViewModel,
    drinkId: String) {
    val cocktailsRequestState by viewModel.cocktailsState
    val drink = viewModel.cocktailsState.value.cocktailList

    LaunchedEffect(Unit) {
        viewModel.getCocktailById(drinkId)
    }


    Box(modifier = Modifier
        .fillMaxSize()) {

        when {
            cocktailsRequestState.loading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
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
                ShowCocktailDetails(drink)
            }
        }
    }
}

@Composable
fun ShowCocktailDetails(drink: List<Drink>) {
    val cocktailLst = cocktailIngredientsList(drink)

    Box(Modifier
        .fillMaxSize()
        .padding(10.dp)
    ) {
        Column(Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
          ) {
            Text(
                text = drink[0].strDrink,
                Modifier
                    .padding(10.dp),
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium,
            )
            Spacer(Modifier.size(20.dp))
            Row() {
                Column(Modifier.width(200.dp)) {
                    Text(
                        text = "Glass",
                        Modifier
                            .padding(10.dp, 0.dp, 0.dp, 0.dp),
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Text(
                            text = drink[0].strGlass,
                    Modifier
                        .padding(10.dp, 0.dp, 0.dp, 0.dp),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    )
                    Text(
                        text = "Ingredients",
                        Modifier
                            .padding(10.dp, 15.dp, 0.dp, 0.dp),
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Column(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(10.dp, 0.dp, 0.dp, 0.dp),
                    ) {
                        for (i in cocktailLst.indices) {
                            Text(text = cocktailLst[i],
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal)
                        }
                    }
                }
                Spacer(Modifier.size(10.dp))
                Image(
                    painter = rememberAsyncImagePainter(drink[0].strDrinkThumb),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .border(1.dp, colorResource(id = R.color.black), shape = RoundedCornerShape(5))
                        .clip(RoundedCornerShape(5)),
                )
            }
            Spacer(Modifier.size(10.dp))
            Text(
                text = "Instructions",
                Modifier
                    .padding(10.dp, 15.dp, 0.dp, 0.dp),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
            )

            Text(text = drink[0].strInstructions,
                Modifier
                    .padding(10.dp, 0.dp, 0.dp, 0.dp),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal)
            Spacer(Modifier.size(20.dp))
            Text(
                text = "Category",
                Modifier
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                    .align(Alignment.Start),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
            )
            Text(
                text = drink[0].strCategory,
                Modifier
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                    .align(Alignment.Start),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
            )
            Spacer(Modifier.size(20.dp))
            Text(
                text = "Alcoholic",
                Modifier
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                    .align(Alignment.Start),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
            )
            Text(
                text = drink[0].strAlcoholic,
                Modifier
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                    .align(Alignment.Start),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
            )

        }
    }
}



private fun cocktailIngredientsList(drink: List<Drink>) : MutableList<String> {
    val ingredientList = mutableListOf<String>()
    drink[0].strIngredient1?.let {ingredient -> ingredientList.add("${if(drink[0].strMeasure1 != null) "${drink[0].strMeasure1} of " else ""}$ingredient")}
    drink[0].strIngredient2?.let {ingredient -> ingredientList.add("${if(drink[0].strMeasure2 != null) "${drink[0].strMeasure2} of " else ""}$ingredient")}
    drink[0].strIngredient3?.let {ingredient -> ingredientList.add("${if(drink[0].strMeasure3 != null) "${drink[0].strMeasure3} of " else ""}$ingredient")}
    drink[0].strIngredient4?.let {ingredient -> ingredientList.add("${if(drink[0].strMeasure4 != null) "${drink[0].strMeasure4} of "  else ""}$ingredient")}
    drink[0].strIngredient5?.let {ingredient -> ingredientList.add("${if(drink[0].strMeasure5 != null) "${drink[0].strMeasure5} of " else ""}$ingredient")}
    drink[0].strIngredient6?.let {ingredient -> ingredientList.add("${if(drink[0].strMeasure6 != null) "${drink[0].strMeasure6} of " else ""}$ingredient")}
    drink[0].strIngredient7?.let {ingredient -> ingredientList.add("${if(drink[0].strMeasure7 != null) "${drink[0].strMeasure7} of " else ""}$ingredient")}
    drink[0].strIngredient8?.let {ingredient -> ingredientList.add("${if(drink[0].strMeasure8 != null) "${drink[0].strMeasure8} of " else ""}$ingredient")}
    drink[0].strIngredient9?.let {ingredient -> ingredientList.add("${if(drink[0].strMeasure9 != null) "${drink[0].strMeasure9} of " else ""}$ingredient")}
    drink[0].strIngredient10?.let {ingredient -> ingredientList.add("${if(drink[0].strMeasure10 != null) "${drink[0].strMeasure10} of " else ""}$ingredient")}
    drink[0].strIngredient11?.let {ingredient -> ingredientList.add("${if(drink[0].strMeasure11 != null) "${drink[0].strMeasure11} of " else ""}$ingredient")}
    drink[0].strIngredient12?.let {ingredient -> ingredientList.add("${if(drink[0].strMeasure12 != null) "${drink[0].strMeasure12} of " else ""}$ingredient")}
    drink[0].strIngredient13?.let {ingredient -> ingredientList.add("${if(drink[0].strMeasure13 != null) "${drink[0].strMeasure13} of " else ""}$ingredient")}
    drink[0].strIngredient14?.let {ingredient -> ingredientList.add("${if(drink[0].strMeasure14 != null) "${drink[0].strMeasure14} of " else ""}$ingredient")}
    drink[0].strIngredient15?.let {ingredient -> ingredientList.add("${if(drink[0].strMeasure15 != null) "${drink[0].strMeasure15} of " else ""}$ingredient")}
    return ingredientList
}