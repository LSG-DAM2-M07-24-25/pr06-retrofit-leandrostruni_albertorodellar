package com.example.cocktailapi.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cocktailapi.model.DrinkEntity

/**
 * Base de datos Room para almacenar información de los cócteles.
 *
 * Define la estructura de la base de datos e incluye el DAO [DrinkDao] para la gestión de datos.
 *
 * @property version Número de versión de la base de datos.
 */
@Database(entities = [DrinkEntity::class], version = 1)
abstract class DrinkDatabase : RoomDatabase() {
    /**
     * Proporciona acceso a las operaciones definidas en [DrinkDao].
     *
     * @return Instancia de [DrinkDao] para acceder a los datos de los cócteles.
     */
    abstract fun drinkDao(): DrinkDao
}
