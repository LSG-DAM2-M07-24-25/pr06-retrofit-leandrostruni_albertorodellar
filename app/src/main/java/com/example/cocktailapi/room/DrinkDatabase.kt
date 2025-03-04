package com.example.cocktailapi.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cocktailapi.model.DrinkEntity

@Database(entities = [DrinkEntity::class], version = 1)
abstract class DrinkDatabase : RoomDatabase() {
    abstract fun drinkDao(): DrinkDao
}
