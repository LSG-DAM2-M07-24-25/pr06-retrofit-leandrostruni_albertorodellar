package com.example.cocktailapi.room
import android.util.Log
import com.example.cocktailapi.model.DrinkEntity

class DrinkRepository {
    val daoInterface: DrinkDao = DrinkApplication.database.drinkDao()

    suspend fun getFavorites(): MutableList<DrinkEntity> = daoInterface.getFavorites()
    suspend fun getDrinkById(id: String): DrinkEntity? = daoInterface.getDrinkById(id)
    suspend fun isFavorite(id: String): Boolean = daoInterface.isFavorite(id)
    suspend fun addFavorite(drink: DrinkEntity) {
        try {
            daoInterface.addFavorite(drink)
        } catch (e: Exception) {
            // Logueamos el error si no se puede agregar a favoritos
            Log.e("Database Error", "Error al agregar bebida favorita: ${e.message}")
        }
    }    suspend fun removeFavorite(drink: DrinkEntity) = daoInterface.removeFavorite(drink)
}
