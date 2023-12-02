package com.flexidevapps.sipsearch.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexidevapps.sipsearch.data.api.ApiInstance
import com.flexidevapps.sipsearch.data.api.CocktailApi
import com.flexidevapps.sipsearch.data.api.Cocktails
import com.flexidevapps.sipsearch.data.api.Drink
import com.flexidevapps.sipsearch.data.repository.CocktailApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit

class SipSearchViewModel(
    private val cocktailApiRepository: CocktailApiRepository
): ViewModel() {


    val cocktailsList: MutableLiveData<Response<Cocktails>> = MutableLiveData()


      fun getCocktailByName(name:String) {
        viewModelScope.launch {
            try {
                val response = cocktailApiRepository.searchCocktailByName(name)
                if(response.isSuccessful) {
                    cocktailsList.value = response
                }
            } catch (e: Exception) {
                error("Error: ${e.message}")

            }

        }

    }

}