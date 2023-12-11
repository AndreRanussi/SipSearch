package com.flexidevapps.sipsearch.data.repository

import com.flexidevapps.sipsearch.data.api.ApiInstance

class CocktailApiRepository(private val apiInstance: ApiInstance) {

    suspend fun searchCocktailByName(name:String) = apiInstance.api.searchCocktailName(name)

    suspend fun searchCocktailByLetter(letter:String) = apiInstance.api.searchCocktailByLetter(letter)

    suspend fun getRandomCocktail() = apiInstance.api.getRandomCocktail()

    suspend fun getCocktailById(drinkId: String) = apiInstance.api.getCocktailById(drinkId)

}