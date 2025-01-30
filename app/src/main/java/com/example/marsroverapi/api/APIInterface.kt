package com.example.marsroverapi.api

import com.example.marsroverapi.model.DatosAPI
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    @GET("rovers/curiosity/photos")
    suspend fun getMarsPhotos(
        @Query("sol") sol: Int,
        @Query("api_key") apiKey: String
    ): Response<DatosAPI>

    companion object{
        private const val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/"

        fun create(): APIInterface{
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