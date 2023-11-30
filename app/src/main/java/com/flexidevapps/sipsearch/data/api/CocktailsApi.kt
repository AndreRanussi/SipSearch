package com.flexidevapps.sipsearch.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CocktailsApi {

    @GET("api/json/v1/1/search.php?s=")
    suspend fun searchCocktails(
        @Query("name") name:String
    ): Response<Cocktails>



}