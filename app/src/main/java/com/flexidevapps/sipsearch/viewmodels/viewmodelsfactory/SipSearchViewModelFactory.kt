package com.flexidevapps.sipsearch.viewmodels.viewmodelsfactory

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flexidevapps.sipsearch.viewmodels.SipSearchViewModel

class SipSearchViewModelFactory(
    private val color: Color
):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SipSearchViewModel(color) as T
    }
}