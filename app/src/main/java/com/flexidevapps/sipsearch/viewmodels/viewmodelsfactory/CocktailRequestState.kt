package com.flexidevapps.sipsearch.viewmodels.viewmodelsfactory

import com.flexidevapps.sipsearch.data.api.Cocktails
import com.flexidevapps.sipsearch.data.api.Drink

data class CocktailRequestState(
    var loading: Boolean = true,
    var cocktailList: List<Drink> = emptyList(),
    val error: String? = null


    )
