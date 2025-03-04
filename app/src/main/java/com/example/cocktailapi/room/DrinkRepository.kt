package com.example.cocktailapi.room
import com.example.cocktailapi.model.DrinkEntity

class DrinkRepository {
    val daoInterface: DrinkDao = DrinkApplication.database.drinkDao()

    suspend fun getFavorites(): MutableList<DrinkEntity> = daoInterface.getFavorites()
    suspend fun getDrinkById(id: String): DrinkEntity? = daoInterface.getDrinkById(id)
    suspend fun isFavorite(id: String): Boolean = daoInterface.isFavorite(id)
    suspend fun addFavorite(drink: DrinkEntity) = daoInterface.addFavorite(drink)
    suspend fun removeFavorite(drink: DrinkEntity) = daoInterface.removeFavorite(drink)
}
