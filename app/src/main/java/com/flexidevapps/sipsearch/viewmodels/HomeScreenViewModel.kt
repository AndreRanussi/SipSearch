package com.flexidevapps.sipsearch.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexidevapps.sipsearch.data.repository.CocktailApiRepository
import com.flexidevapps.sipsearch.util.CocktailRequestState
import kotlinx.coroutines.launch

class HomeScreenViewModel(
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
                    error = "No Cocktail"
                )

            }

        }
    }

    fun getRandomCocktail(){
        viewModelScope.launch {
            try {
                val response = cocktailApiRepository.getRandomCocktail()
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

    fun searchCocktailLetter(letter:String) {
        viewModelScope.launch {
            try {
                val response = cocktailApiRepository.searchCocktailByLetter(letter)
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

    fun clearCocktailList() {
        _cocktailsState.value = _cocktailsState.value.copy(
            cocktailList = emptyList()
        )
    }


}

