package com.flexidevapps.sipsearch.ui.homepage

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModelProvider
import com.flexidevapps.sipsearch.R
import com.flexidevapps.sipsearch.data.api.ApiInstance
import com.flexidevapps.sipsearch.data.repository.CocktailApiRepository
import com.flexidevapps.sipsearch.ui.theme.SipSearchTheme
import com.flexidevapps.sipsearch.viewmodels.SipSearchViewModel
import com.flexidevapps.sipsearch.viewmodels.viewmodelsfactory.SipSearchViewModelFactory

class SipSearchActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                SipSearchTheme {
                  Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                      val cocktailApi = ApiInstance()
                      val repository = CocktailApiRepository(cocktailApi)
                      val factory = SipSearchViewModelFactory(repository)
                      val viewModel = ViewModelProvider(this, factory).get(SipSearchViewModel::class.java)


                    SipSearch(viewModel)

                }
            }
        }
    }
}



@Composable
fun SipSearch(viewModel: SipSearchViewModel) {
    val TAG = "SipActivity"
    Box(modifier = Modifier
        .fillMaxSize()
        .paint(
            painterResource(id = R.drawable.main_background_image),
            contentScale = ContentScale.FillBounds
        )
    ) {
        viewModel.getCocktailByName("Long")

      Column(
          modifier = Modifier.fillMaxWidth(),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
      ) {
          val context = LocalContext.current

          Button(onClick = {
              val drink = viewModel.cocktailsList.value?.body()?.drinks?.get(1)
//
                Toast.makeText(context, "size:${viewModel.cocktailsList.value?.body()?.drinks?.size} | ID: ${drink?.idDrink} | Name: ${drink?.strDrink} | Instructions: ${drink?.strInstructions}", Toast.LENGTH_SHORT).show()

          }) {
              Text(text = "Test")

          }
      }
        
    }
    

}