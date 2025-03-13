package com.example.cocktailapi.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.cocktailapi.model.DrinkEntity

/**
 * Interfaz DAO (Data Access Object) para la gestión de datos de cócteles en la base de datos Room.
 *
 * Define las operaciones para obtener, insertar y eliminar cócteles favoritos.
 */
@Dao
interface DrinkDao {
     /**
     * Obtiene todas las bebidas marcadas como favoritas en la base de datos.
     *
     * @return Lista mutable de cócteles favoritos.
     */
    @Query("SELECT * FROM drinks WHERE is_favorite = 1")
    fun getFavorites(): MutableList<DrinkEntity>

     /**
     * Verifica si un cóctel está marcado como favorito.
     *
     * @param id Identificador único del cóctel.
     * @return `true` si el cóctel es favorito, `false` en caso contrario.
     */
    @Query("SELECT is_favorite FROM drinks WHERE idDrink = :id")
    fun isFavorite(id: String): Boolean

     /**
     * Agrega un cóctel a la lista de favoritos.
     *
     * @param favoriteDrink Objeto [DrinkEntity] que representa el cóctel a agregar.
     */
    @Insert
    fun addFavorite(favoriteDrink: DrinkEntity)

     /**
     * Elimina un cóctel de la lista de favoritos.
     *
     * @param favoriteDrink Objeto [DrinkEntity] que representa el cóctel a eliminar.
     */
    @Delete
    fun removeFavorite(favoriteDrink: DrinkEntity)
}
