package com.flexidevapps.sipsearch.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.flexidevapps.sipsearch.R
import com.flexidevapps.sipsearch.data.api.ApiInstance
import com.flexidevapps.sipsearch.data.repository.CocktailApiRepository
import com.flexidevapps.sipsearch.ui.cocktaildetailspage.CocktailDetailsScreen
import com.flexidevapps.sipsearch.ui.homepage.HomeScreen
import com.flexidevapps.sipsearch.ui.theme.SipSearchTheme
import com.flexidevapps.sipsearch.viewmodels.HomeScreenViewModel
import com.flexidevapps.sipsearch.viewmodels.CocktailDetailsScreenViewModel
import com.flexidevapps.sipsearch.viewmodels.viewmodelsfactory.CocktailDetailsScreenViewModelFactory
import com.flexidevapps.sipsearch.viewmodels.viewmodelsfactory.SipSearchViewModelFactory

class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            val homeScreenViewModel = ViewModelProvider(this, homeScreenVmFactory()).get(
                HomeScreenViewModel::class.java)

            val detailsScreenViewModel = ViewModelProvider(this, detailsScreenVmFactory()).get(
                CocktailDetailsScreenViewModel::class.java)

            installSplashScreen().setKeepOnScreenCondition { homeScreenViewModel.cocktailsState.value.loading }
            window.statusBarColor = getColor(R.color.black)
        window.navigationBarColor = getColor(R.color.black)
        setContent {
            SipSearchTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(homeScreenViewModel, detailsScreenViewModel)
                }
            }
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MyApp(homeScreenViewModel: HomeScreenViewModel,
          detailsScreenViewModel: CocktailDetailsScreenViewModel
          ) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home-screen") {
        composable("home-screen") {
            HomeScreen(viewModel = homeScreenViewModel, navigationToDetailsScreen = { drinkId ->
                navController.navigate("cocktail-details-screen/$drinkId")
            })
        }
        composable("cocktail-details-screen/{drinkId}"){
            val drinkId = it.arguments?.getString("drinkId") ?: "No Cocktail"
             CocktailDetailsScreen(drinkId = drinkId, viewModel = detailsScreenViewModel)
        }
    }
}

private fun homeScreenVmFactory(): SipSearchViewModelFactory {
    val cocktailApi = ApiInstance()
    val repository = CocktailApiRepository(cocktailApi)
    return SipSearchViewModelFactory(repository)
}

private fun detailsScreenVmFactory(): CocktailDetailsScreenViewModelFactory {
    val cocktailApi = ApiInstance()
    val repository = CocktailApiRepository(cocktailApi)
    return CocktailDetailsScreenViewModelFactory(repository)
}