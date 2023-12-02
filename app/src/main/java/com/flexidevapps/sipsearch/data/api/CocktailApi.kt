package com.flexidevapps.sipsearch.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApi {

    @GET("api/json/v1/1/search.php")
    suspend fun searchCocktailName(
        @Query("s")name: String
    ) : Response<Cocktails>

    @GET("api/json/v1/1/search.php")
    suspend fun searchCocktailLetter(
        @Query("f")letter: String
    ): Response<Cocktails>

    @GET("api/json/v1/1/search.php")
    suspend fun searchCocktailIngredientName(
        @Query("i")ingredient: String
    ): Response<Cocktails>
}