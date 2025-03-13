package com.example.cocktailapi.room

import android.app.Application
import androidx.room.Room

/**
 * Clase `Application` que inicializa la base de datos Room al iniciar la aplicación.
 *
 * Utiliza el patrón Singleton para garantizar una única instancia de la base de datos.
 */
class DrinkApplication : Application() {
    /**
     * Instancia única de la base de datos accesible en toda la aplicación.
     */
    companion object {
        lateinit var database: DrinkDatabase
    }

    override fun onCreate() {
        super.onCreate()
        // Configuración de la base de datos Room
        database = Room.databaseBuilder(
            this,
            DrinkDatabase::class.java,
            "DrinkDatabase"
        ).build()
    }
}
