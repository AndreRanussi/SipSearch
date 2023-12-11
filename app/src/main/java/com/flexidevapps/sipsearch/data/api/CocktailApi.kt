package com.flexidevapps.sipsearch.data.api

import com.flexidevapps.sipsearch.data.Cocktails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApi {

    @GET("api/json/v1/1/search.php")
    suspend fun searchCocktailName(
        @Query("s")name: String
    ) : Response<Cocktails>

    @GET("api/json/v1/1/search.php")
    suspend fun searchCocktailByLetter(
        @Query("f")letter: String
    ): Response<Cocktails>

    @GET("api/json/v1/1/search.php")
    suspend fun searchCocktailIngredientName(
        @Query("i")ingredient: String
    ): Response<Cocktails>

    @GET("api/json/v1/1/random.php")
    suspend fun getRandomCocktail(
    ): Response<Cocktails>

    @GET("api/json/v1/1/lookup.php")
    suspend fun getCocktailById(
        @Query("i")drinkId: String
    ): Response<Cocktails>

}

