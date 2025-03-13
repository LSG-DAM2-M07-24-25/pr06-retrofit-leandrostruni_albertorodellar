package com.example.cocktailapi.api

import com.example.cocktailapi.model.DataAPI
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interfaz que define los métodos de la API de TheCocktailDB.
 * Utiliza Retrofit para realizar llamadas HTTP.
 */
interface APIInterface {

    /**
     * Busca un cóctel por su nombre.
     *
     * @param name Nombre del cóctel a buscar.
     * @return Una respuesta de tipo [Response] que contiene un objeto [DataAPI] con los resultados.
     *
     * Ejemplo de llamada: `www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita`
     */
    //www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita
    @GET("search.php")
    suspend fun searchCocktailByName(
        @Query("s") name: String
    ): Response<DataAPI>

    /**
     * Obtiene un cóctel aleatorio.
     *
     * @return Una respuesta de tipo [Response] que contiene un objeto [DataAPI] con un cóctel aleatorio.
     *
     */
    @GET("random.php")
    suspend fun getRandomCocktail(): Response<DataAPI>

    /**
     * Obtiene una lista de cócteles filtrados por categoría.
     *
     * @param category Categoría del cóctel (por ejemplo, "Cocktail", "Ordinary_Drink").
     * @return Una respuesta de tipo [Response] con la lista de cócteles que pertenecen a la categoría dada.
     *
     * Ejemplo de llamada:
     * - `www.thecocktaildb.com/api/json/v1/1/filter.php?c=Ordinary_Drink`
     * - `www.thecocktaildb.com/api/json/v1/1/filter.php?c=Cocktail`
     */
    @GET("filter.php")
    suspend fun getCocktailByCategory(
        @Query("c") category: String
    ): Response<DataAPI>

    /**
     * Obtiene la lista de todas las categorías de cócteles disponibles en la API.
     *
     * @return Una respuesta de tipo [Response] con un objeto [DataAPI] que contiene la lista de categorías.
     *
     * Ejemplo de llamada: `www.thecocktaildb.com/api/json/v1/1/list.php?c=list`
     */
    @GET("list.php?c=list")
    suspend fun getCategories(): Response<DataAPI>

    /**
     * Obtiene los detalles de un cóctel específico a partir de su ID.
     *
     * @param id ID del cóctel a consultar.
     * @return Una respuesta de tipo [Response] que contiene un objeto [DataAPI] con los detalles del cóctel.
     *
     * Ejemplo de llamada: `www.thecocktaildb.com/api/json/v1/1/lookup.php?i=11007`
     */
    @GET("lookup.php")
    suspend fun getCocktailById(
        @Query("i") id: String
    ): Response<DataAPI>

    /**
     * Objeto `companion` para la creación de una instancia de la API.
     */
    companion object {
        private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

        /**
         * Crea una instancia de [APIInterface] con Retrofit.
         *
         * @return Una implementación de la interfaz [APIInterface] con la configuración de Retrofit.
         */
        fun create(): APIInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(APIInterface::class.java)
        }
    }
}