package com.flexidevapps.sipsearch.viewmodels.viewmodelsfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flexidevapps.sipsearch.data.repository.CocktailApiRepository
import com.flexidevapps.sipsearch.viewmodels.HomeScreenViewModel

class SipSearchViewModelFactory(
    private val cocktailApiRepository: CocktailApiRepository
):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeScreenViewModel(cocktailApiRepository) as T
    }
}
