package com.example.cocktailapi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktailapi.api.Repository
import com.example.cocktailapi.model.DataAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel que maneja las solicitudes a la API y la gestión del estado de la interfaz de usuario.
 */
class APIViewModel : ViewModel() {
    private val repository = Repository()

    /** Estado de carga de la API */
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    /** Datos obtenidos de la API */
    private val _cocktailData = MutableLiveData<DataAPI?>()
    val cocktailData: LiveData<DataAPI?> = _cocktailData

    /**
     * Busca un cóctel por su nombre.
     *
     * @param name Nombre del cóctel a buscar.
     */
    fun searchCocktail(name: String) {

        if (name.isBlank()) {
            return
        }

        _cocktailData.value = null
        _loading.value = true

        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.searchCocktailByName(name)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _cocktailData.value = response.body()
                } else {
                    Log.e("Error API", response.message())
                }
                _loading.value = false
            }
        }
    }

    /**
     * Obtiene un cóctel aleatorio.
     */
    fun fetchRandomCocktail() {
        _cocktailData.value = null
        _loading.value = true

        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getRandomCocktail()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _cocktailData.value = response.body()
                } else {
                    Log.e("Error API", response.message())
                }
                _loading.value = false
            }
        }
    }

    /**
     * Obtiene los detalles de un cóctel por su ID.
     *
     * @param id Identificador único del cóctel.
     */
    fun getCocktailById(id: String) {
        _cocktailData.value = null
        _loading.value = true

        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getCocktailById(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _cocktailData.value = response.body()
                    Log.d("DetailsScreen vm", "getCocktailById: ${_cocktailData.value}")
                } else {
                    Log.e("Error API", response.message())
                }
                _loading.value = false
            }
        }
    }

    /**
     * Limpia los datos almacenados de la API.
     */
    fun clearCocktailData() {
        _cocktailData.value = null
    }
}