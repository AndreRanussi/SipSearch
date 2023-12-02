package com.flexidevapps.sipsearch.data.api

import com.flexidevapps.sipsearch.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiInstance {

    companion object {

        private val retrofit: Retrofit by lazy {

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }


    val api by lazy {
        retrofit.create(CocktailApi::class.java)
    }
}