package com.example.marsroverapi.api

class Repository {
    private val apiInterface = APIInterface.create()

    suspend fun getMarsPhotos(sol: Int, apiKey: String) = apiInterface.getMarsPhotos(sol, apiKey)
}