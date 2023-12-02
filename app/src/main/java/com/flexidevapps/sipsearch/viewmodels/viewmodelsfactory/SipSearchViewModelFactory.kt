package com.flexidevapps.sipsearch.viewmodels.viewmodelsfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flexidevapps.sipsearch.data.api.ApiInstance
import com.flexidevapps.sipsearch.data.api.CocktailApi
import com.flexidevapps.sipsearch.data.repository.CocktailApiRepository
import com.flexidevapps.sipsearch.viewmodels.SipSearchViewModel

class SipSearchViewModelFactory(
    private val cocktailApiRepository: CocktailApiRepository
):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SipSearchViewModel(cocktailApiRepository) as T
    }
}