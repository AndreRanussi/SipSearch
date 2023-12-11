package com.flexidevapps.sipsearch.util

import com.flexidevapps.sipsearch.data.Drink

data class CocktailRequestState(
    var loading: Boolean = true,
    var cocktailList: List<Drink> = emptyList(),
    val error: String? = null

    )
