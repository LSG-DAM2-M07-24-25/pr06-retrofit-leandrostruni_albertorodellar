package com.example.cocktailapi.api

class Repository {
    private val apiInterface = APIInterface.create()

    suspend fun searchCocktailByName(name: String) = apiInterface.searchCocktailByName(name)
    suspend fun getRandomCocktail() = apiInterface.getRandomCocktail()
}


//Opción con patron Singleton para asegurar una unica instancia
/*
class MarsRepository(private val apiInterface: APIInterface) {

    suspend fun getMarsPhotos(sol: Int, apiKey: String): Response<DatosAPI> {
        return apiInterface.getMarsPhotos(sol, apiKey)
    }

    companion object {
        private var instance: Repository? = null

        fun getInstance(apiInterface: APIInterface): Repository {
            if (instance == null) {
                instance = Repository(apiInterface)
            }
            return instance!!
        }
    }
}
*/