package com.flexidevapps.sipsearch.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexidevapps.sipsearch.data.api.Cocktails
import com.flexidevapps.sipsearch.data.repository.CocktailApiRepository
import com.flexidevapps.sipsearch.viewmodels.viewmodelsfactory.CocktailRequestState
import kotlinx.coroutines.launch
import retrofit2.Response

class SipSearchViewModel(
    private val cocktailApiRepository: CocktailApiRepository
): ViewModel() {

    private val _cocktailsState = mutableStateOf(CocktailRequestState())
    val cocktailsState: State<CocktailRequestState> = _cocktailsState

      fun getCocktailByName(name:String){
        viewModelScope.launch {
            try {
                val response = cocktailApiRepository.searchCocktailByName(name)
                _cocktailsState.value = _cocktailsState.value.copy(
                    loading = false,
                    cocktailList = response.body()!!.drinks,
                    error = null
                  )
            } catch (e: Exception) {
                _cocktailsState.value = _cocktailsState.value.copy(
                    loading = false,
                    error = "Error while training to get your cocktail(s) ${e.message}"
                )

            }

        }
    }

}