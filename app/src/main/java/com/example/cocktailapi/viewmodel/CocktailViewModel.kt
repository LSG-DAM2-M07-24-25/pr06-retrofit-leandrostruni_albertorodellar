package com.example.cocktailapi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktailapi.api.Repository
import com.example.cocktailapi.model.DataAPI
import com.example.cocktailapi.model.Drink
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
            _cocktailData.value = DataAPI(emptyList())
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
}