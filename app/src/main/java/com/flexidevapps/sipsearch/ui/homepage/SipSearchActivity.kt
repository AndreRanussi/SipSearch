package com.flexidevapps.sipsearch.ui.homepage

import android.icu.text.DisplayContext
import android.net.http.HttpException
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.flexidevapps.sipsearch.R
import com.flexidevapps.sipsearch.data.api.RetrofitInstance
import com.flexidevapps.sipsearch.ui.theme.SipSearchTheme
import com.flexidevapps.sipsearch.viewmodels.SipSearchViewModel
import com.flexidevapps.sipsearch.viewmodels.viewmodelsfactory.SipSearchViewModelFactory
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.io.IOException
const val TAG ="SipSearchActivity"

class SipSearchActivity : ComponentActivity() {
      @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

          val factory = SipSearchViewModelFactory(Color.Cyan)
          val viewModel = ViewModelProvider(this, factory).get(SipSearchViewModel::class.java)





        setContent {
            window.statusBarColor = getColor(R.color.black)
            window.navigationBarColor = getColor(R.color.black)

            SipSearchTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = viewModel.backgroundColor
                ) {
                    SipSearch(viewModel)
                }
            }
        }
    }

}



@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
private fun SipSearch(
    viewModel: SipSearchViewModel
){
    val coroutineScope = rememberCoroutineScope()

      Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
      ) {
          val context = LocalContext.current
          Button(onClick = {
              viewModel.changeBackGroundColor()

              coroutineScope.launch {
                  val response = try {
                      RetrofitInstance.api.searchCocktails("martini")
                  } catch (e: IOException) {
                        Log.e(TAG, "IOException, you might not have internet connection")
                        return@launch
                  } catch (e: HttpException) {
                      Log.e(TAG, "HttpException, unexpected response")
                      return@launch
                  }
                  if (response.isSuccessful && response.body() != null) {
                      val drink = response.body()

                      val name = drink?.drinks?.get(0)?.strDrink



                      Log.e(TAG, response.body().toString())
                      Toast.makeText(context, "Drink Name: $name, Drink Ingredient: ", Toast.LENGTH_SHORT ).show()
                  }
              }


          }) {
              Text(text = "Change Color")

              
          }
      }
    

    
}