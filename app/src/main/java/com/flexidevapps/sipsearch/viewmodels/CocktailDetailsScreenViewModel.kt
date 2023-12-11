package com.flexidevapps.sipsearch.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexidevapps.sipsearch.data.repository.CocktailApiRepository
import com.flexidevapps.sipsearch.util.CocktailRequestState
import kotlinx.coroutines.launch

class CocktailDetailsScreenViewModel(
    private val cocktailApiRepository: CocktailApiRepository
):ViewModel() {

    private val _cocktailsState = mutableStateOf(CocktailRequestState())
    val cocktailsState: State<CocktailRequestState> = _cocktailsState

    fun getCocktailById(drinkId: String){
        viewModelScope.launch {
            try {
                val response = cocktailApiRepository.getCocktailById(drinkId)
                _cocktailsState.value = _cocktailsState.value.copy(
                    loading = false,
                    cocktailList = response.body()!!.drinks,
                    error = null
                )
            } catch (e: Exception) {
                _cocktailsState.value = _cocktailsState.value.copy(
                    loading = false,
                    error = "No Cocktail"
                )

            }
        }
    }


}