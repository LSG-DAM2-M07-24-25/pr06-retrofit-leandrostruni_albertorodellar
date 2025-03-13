package com.example.cocktailapi.room
import android.util.Log
import com.example.cocktailapi.model.DrinkEntity

class DrinkRepository {
    private val daoInterface: DrinkDao = DrinkApplication.database.drinkDao()

    suspend fun getFavorites(): MutableList<DrinkEntity> = daoInterface.getFavorites()
    suspend fun isFavorite(id: String): Boolean = daoInterface.isFavorite(id)
    suspend fun addFavorite(drink: DrinkEntity) {
        try {
            daoInterface.addFavorite(drink)
        } catch (e: Exception) {
            Log.e("Database Error", "Error al agregar bebida favorita: ${e.message}")
        }
    }
    suspend fun removeFavorite(drink: DrinkEntity) = daoInterface.removeFavorite(drink)
}
