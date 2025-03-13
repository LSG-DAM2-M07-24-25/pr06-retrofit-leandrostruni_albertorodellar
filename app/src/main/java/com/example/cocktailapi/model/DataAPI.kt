package com.example.cocktailapi.model

/**
 * Modelo de datos que representa la respuesta de la API al realizar consultas sobre cócteles.
 *
 * La API devuelve una lista de objetos [Drink], cada uno de los cuales contiene información
 * detallada sobre un cóctel.
 *
 * @property drinks Lista de cócteles obtenidos en la respuesta de la API.
 */
data class DataAPI(
    val drinks: List<Drink>
)