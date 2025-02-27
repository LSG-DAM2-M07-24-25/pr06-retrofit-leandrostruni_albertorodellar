package com.example.cocktailapi.api

class Repository {
    private val apiInterface = APIInterface.create()

    suspend fun searchCocktailByName(name: String) = apiInterface.searchCocktailByName(name)
    suspend fun getRandomCocktail() = apiInterface.getRandomCocktail()
    suspend fun getCocktailByCategory(category: String) = apiInterface.getCocktailByCategory(category)
}
