package com.example.cocktailapi.viewmodel

import androidx.lifecycle.ViewModel

class CocktailViewModel: ViewModel () {

    fun fetchFilteredCocktails(apiViewModel: APIViewModel, isOrdinary: Boolean, isCocktail: Boolean) {
        val categories = mutableListOf<String>()
        if (isOrdinary) categories.add("Ordinary_Drink")
        if (isCocktail) categories.add("Cocktail")

        if (categories.isNotEmpty()) {
            apiViewModel.fetchCocktailByCategory(categories)
        } else {
            apiViewModel.clearCocktails()
        }
    }
}