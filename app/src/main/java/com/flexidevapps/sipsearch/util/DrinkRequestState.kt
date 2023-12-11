package com.flexidevapps.sipsearch.util

import com.flexidevapps.sipsearch.data.Drink

data class DrinkRequestState(
    var loading: Boolean = true,
    var drink: Drink? = null,
    val error: String? = null

    )
