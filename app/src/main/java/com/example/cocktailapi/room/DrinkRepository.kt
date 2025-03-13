package com.example.cocktailapi.room
import android.util.Log
import com.example.cocktailapi.model.DrinkEntity

/**
 * Repositorio que actúa como capa de abstracción entre la base de datos Room y la UI.
 *
 * Permite acceder a los datos de cócteles favoritos de manera segura y estructurada.
 */
class DrinkRepository {
    /** Instancia del DAO para interactuar con la base de datos. */
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
