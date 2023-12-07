package com.flexidevapps.sipsearch.ui.homepage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.flexidevapps.sipsearch.R
import com.flexidevapps.sipsearch.data.api.ApiInstance
import com.flexidevapps.sipsearch.data.api.SipSearch
import com.flexidevapps.sipsearch.data.repository.CocktailApiRepository
import com.flexidevapps.sipsearch.ui.theme.SipSearchTheme
import com.flexidevapps.sipsearch.viewmodels.SipSearchViewModel
import com.flexidevapps.sipsearch.viewmodels.viewmodelsfactory.SipSearchViewModelFactory

class SipSearchActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        window.statusBarColor = getColor(R.color.black)
        window.navigationBarColor = getColor(R.color.black)
        setContent {
                SipSearchTheme {
                  Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                      val viewModel = ViewModelProvider(this, viewModelFactoryParams()).get(SipSearchViewModel::class.java)
                      SipSearch(viewModel)

                }
            }
        }
    }
}


private fun viewModelFactoryParams(): SipSearchViewModelFactory {
    val cocktailApi = ApiInstance()
    val repository = CocktailApiRepository(cocktailApi)
    return SipSearchViewModelFactory(repository)
}