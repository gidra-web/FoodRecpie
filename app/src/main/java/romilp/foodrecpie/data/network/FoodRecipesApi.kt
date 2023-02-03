package romilp.foodrecpie.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import romilp.foodrecpie.model.FoodRecipe

interface FoodRecipesApi {

    @GET("recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries: Map<String, String>
    ): Response<FoodRecipe>             //metode za getRecipes i searchRecipes

    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @QueryMap searchQuery: Map<String, String>
    ): Response<FoodRecipe>

}