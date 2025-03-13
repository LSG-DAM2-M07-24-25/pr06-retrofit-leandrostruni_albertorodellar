package com.example.cocktailapi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktailapi.api.Repository
import com.example.cocktailapi.model.DataAPI
import com.example.cocktailapi.model.Drink
import com.example.cocktailapi.model.DrinkEntity
import com.example.cocktailapi.room.DrinkRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CocktailViewModel : ViewModel() {
    private val repository = Repository()
    private val _cocktailData = MutableLiveData<DataAPI?>()
    val cocktailData: LiveData<DataAPI?> = _cocktailData
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _selectedCocktailId = MutableLiveData<String?>()
    val selectedCocktailId: LiveData<String?> = _selectedCocktailId

    private val _categories = MutableLiveData<List<String?>>()
    val categories: LiveData<List<String?>> = _categories
    private var currentCategories: List<String?>? = null

    fun fetchCategories() {
        setCurrentScreen("categories")
        _loading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getCategories()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _categories.value =
                        response.body()?.drinks?.map { it.strCategory } ?: emptyList()
                } else {
                    Log.e("API Error", "Error al obtener categorías: ${response.message()}")
                }
                _loading.value = false
            }
        }
    }

    fun fetchFilteredCocktails(selectedCategories: List<String>) {
        if (selectedCategories.isEmpty()) {
            clearCocktails()
            return
        }

        if (currentCategories == selectedCategories) {
            return
        } else {
            currentCategories = selectedCategories
        }

        _loading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val drinksList = mutableListOf<Drink>()

            for (category in selectedCategories) {
                val response = repository.getCocktailByCategory(category)
                Log.d(
                    "API Response",
                    "Categoría: $category - Código: ${response.code()} - Cuerpo: ${response.body()}"
                )
                if (response.isSuccessful) {
                    response.body()?.drinks?.let { drinksList.addAll(it) }
                } else {
                    Log.e(
                        "API Error",
                        "Error al obtener cócteles de $category: ${response.message()}"
                    )
                }
            }

            withContext(Dispatchers.Main) {
                _cocktailData.value = DataAPI(drinksList)
                _searchedCocktails.value = drinksList
                _loading.value = false
            }
        }
    }

    //Mét-odo para guardar el id del cocktail seleccionado para poder detallar info
    fun selectCocktail(id: String) {
        _selectedCocktailId.value = id
    }

    fun clearCocktails() {
        _cocktailData.postValue(DataAPI(emptyList()))
    }

    // ROOM SECTION
    private val drinkRepository = DrinkRepository()

    private val _isFavorite = MutableLiveData(false)
    val isFavorite: LiveData<Boolean> = _isFavorite

    private val _favorites = MutableLiveData<MutableList<DrinkEntity>>()
    val favorites: LiveData<MutableList<DrinkEntity>> = _favorites

    // Obtener todos los favoritos
    fun getFavorites() {
        setCurrentScreen("favorites")
        CoroutineScope(Dispatchers.IO).launch {
            val response = drinkRepository.getFavorites()
            withContext(Dispatchers.Main) {
                _favorites.value = response
                _searchedCocktails.value = response.map { it.toDrink() }
                _loading.value = false
            }
        }
    }

    // Verificar si un cocktail es favorito
    suspend fun isFavorite(idDrink: String): Boolean {
        return withContext(Dispatchers.IO) {
            drinkRepository.isFavorite(idDrink)
        }
    }


    // Añadir cocktail a favoritos
    fun addFavorite(drink: Drink) {
        val drinkEntity = drink.toDrinkEntity(isFavorite = true)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                drinkRepository.addFavorite(drinkEntity)
            } catch (e: Exception) {
                Log.e("Room Error", "Error al añadir a favoritos: ${e.message}")
            }
        }
    }

    // Eliminar cocktail de favoritos
    fun removeFavorite(drink: Drink) {
        val drinkEntity = drink.toDrinkEntity(isFavorite = false)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                drinkRepository.removeFavorite(drinkEntity)
            } catch (e: Exception) {
                Log.e("Room Error", "Error al eliminar de favoritos: ${e.message}")
            }
        }
    }

    // SEARCH BAR SECTION
    private val _searchedText = MutableLiveData("")
    val searchedText: LiveData<String> = _searchedText

    private val _searchHistory = MutableLiveData<List<String>>(emptyList())
    val searchHistory: LiveData<List<String>> = _searchHistory

    private var _searchedCocktails = MutableLiveData<List<Drink>>()
    val searchedCocktails: LiveData<List<Drink>> = _searchedCocktails

    private val _currentScreen = MutableLiveData("favorites")
    val currentScreen: LiveData<String> = _currentScreen

    private fun setCurrentScreen(screen: String) {
        _currentScreen.value = screen
    }

    // Esto observa los cambios en la lista de favoritos y en el texto buscado
    init {
        _searchedText.observeForever { searchCocktail() }
        _searchHistory.observeForever { searchCocktail() }
    }

    fun onSearchTextChange(text: String) {
        this._searchedText.value = text
    }

    fun addToHistory(query: String) {
        if (query.isNotBlank()) {
            _searchHistory.value = _searchHistory.value.orEmpty() + query
        }
    }

    fun clearHistory(totalClear: Boolean = false) {
        this._searchedText.value = ""
        if (totalClear) {
            this._searchHistory.value = emptyList()
        }
    }

    private fun searchCocktail() {
        val query = _searchedText.value.orEmpty().lowercase().trim()

        val results = when (_currentScreen.value) {
            "favorites" -> _favorites.value.orEmpty().filter {
                it.strDrink.contains(query, ignoreCase = true)
            }.map { it.toDrink() }

            "categories" -> _cocktailData.value?.drinks.orEmpty().filter {
                it.strDrink.contains(query, ignoreCase = true)
            }
            else -> emptyList()
        }
        _searchedCocktails.value = results
    }
}