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

    fun fetchCategories() {
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
            _cocktailData.postValue(DataAPI(emptyList()))
            return
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

    private val _isFavorite = MutableLiveData<Boolean>(false)
    val isFavorite: LiveData<Boolean> = _isFavorite

    private val _favorites = MutableLiveData<MutableList<DrinkEntity>>()
    val favorites: LiveData<MutableList<DrinkEntity>> = _favorites

    // Obtener todos los favoritos
    fun getFavorites() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = drinkRepository.getFavorites()
            withContext(Dispatchers.Main) {
                _favorites.value = response
                _loading.value = false
            }
        }
    }

    // Verificar si un cóctel es favorito
    fun isFavorite(idDrink: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = drinkRepository.isFavorite(idDrink)
            withContext(Dispatchers.Main) {
                _isFavorite.value = response
            }
        }
    }

    // Añadir un cóctel a favoritos
    fun addFavorite(drink: Drink) {
        val drinkEntity = drink.toDrinkEntity(isFavorite = true)

        CoroutineScope(Dispatchers.IO).launch {
            drinkRepository.addFavorite(drinkEntity)
        }
    }

    // Eliminar un cóctel de favoritos
    fun removeFavorite(drink: Drink) {
        val drinkEntity = drink.toDrinkEntity(isFavorite = false)

        CoroutineScope(Dispatchers.IO).launch {
            drinkRepository.removeFavorite(drinkEntity)
        }
    }
}