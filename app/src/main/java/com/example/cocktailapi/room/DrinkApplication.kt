package com.example.cocktailapi.room

import android.app.Application
import androidx.room.Room

class DrinkApplication : Application() {
    // Patrón Singleton para la base de datos
    companion object {
        lateinit var database: DrinkDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            DrinkDatabase::class.java,
            "DrinkDatabase"
        ).build()
    }
}
