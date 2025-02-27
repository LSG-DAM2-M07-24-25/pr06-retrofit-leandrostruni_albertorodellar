package com.example.cocktailapi.api

import com.example.cocktailapi.model.DataAPI
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    //www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita
    @GET("search.php")
    suspend fun searchCocktailByName(
        @Query("s") name: String
    ): Response<DataAPI>

    //www.thecocktaildb.com/api/json/v1/1/random.php
    @GET("random.php")
    suspend fun getRandomCocktail(): Response<DataAPI>

    /*
    www.thecocktaildb.com/api/json/v1/1/filter.php?c=Ordinary_Drink
    www.thecocktaildb.com/api/json/v1/1/filter.php?c=Cocktail
    */
    @GET("filter.php")
    suspend fun getCocktailByCategory(
        @Query("c") category: String
    ): Response<DataAPI>

    @GET("list.php?c=list")
    suspend fun getCategories(): Response<DataAPI>

    companion object {
        private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

        fun create(): APIInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(APIInterface::class.java)
        }
    }
}