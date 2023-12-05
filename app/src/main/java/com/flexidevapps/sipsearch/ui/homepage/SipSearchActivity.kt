package com.flexidevapps.sipsearch.ui.homepage

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.flexidevapps.sipsearch.R
import com.flexidevapps.sipsearch.data.api.ApiInstance
import com.flexidevapps.sipsearch.data.api.OutlinedButtonCompose
import com.flexidevapps.sipsearch.data.api.OutlinedDropdownCompose
import com.flexidevapps.sipsearch.data.api.OutlinedTextFieldCompose
import com.flexidevapps.sipsearch.data.repository.CocktailApiRepository
import com.flexidevapps.sipsearch.ui.theme.SipSearchTheme
import com.flexidevapps.sipsearch.viewmodels.SipSearchViewModel
import com.flexidevapps.sipsearch.viewmodels.viewmodelsfactory.SipSearchViewModelFactory

var viewModel: SipSearchViewModel? = null

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
                      viewModel = ViewModelProvider(this, viewModelFactoryParams()).get(SipSearchViewModel::class.java)
                      SipSearch()

                }
            }
        }
    }
}


@Composable
fun SipSearch() {
    var searchText by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxSize()
        .paint(
            painterResource(id = R.drawable.main_background_image),
            contentScale = ContentScale.FillBounds
        )
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(Modifier.size(30.dp))
            Row{
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
                OutlinedTextFieldCompose(searchText,
                    {
                        searchText = it
                    },
                    {
                      Icon(Icons.Default.Search, contentDescription = "Serch")
                    },
                    "Search Text")
            }
            Spacer(modifier = Modifier.size(15.dp))
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                OutlinedButtonCompose("Filter") {
                    //Implement Filter Button action
                }
                OutlinedButtonCompose("Show All") {
                    // //Implement Show All Button action
                }
                OutlinedButtonCompose("Random") {
                    // //Implement Show All Button action
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