package com.example.cocktailapi.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.cocktailapi.model.DrinkEntity

@Dao
interface DrinkDao {
    // Obtener todas las bebidas favoritas
    @Query("SELECT * FROM drinks WHERE is_favorite = 1")
    fun getFavorites(): MutableList<DrinkEntity>

    // Obtener una bebida por su ID
    @Query("SELECT * FROM drinks WHERE idDrink = :id LIMIT 1")
    fun getDrinkById(id: String): DrinkEntity?

    // Comprobar si una bebida es favorita
    @Query("SELECT is_favorite FROM drinks WHERE idDrink = :id")
    fun isFavorite(id: String): Boolean

    // Agregar bebida a favoritos
    @Insert
    fun addFavorite(favoriteDrink: DrinkEntity)

    // Eliminar bebida de favoritos
    @Delete
    fun removeFavorite(favoriteDrink: DrinkEntity)
}
