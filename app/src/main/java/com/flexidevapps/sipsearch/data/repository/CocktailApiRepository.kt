package com.flexidevapps.sipsearch.data.repository

import com.flexidevapps.sipsearch.data.api.ApiInstance

class CocktailApiRepository(private val apiInstance: ApiInstance) {

    suspend fun searchCocktailByName(name:String) = apiInstance.api.searchCocktailName(name)

}