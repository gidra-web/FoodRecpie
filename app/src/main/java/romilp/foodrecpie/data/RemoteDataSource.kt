package romilp.foodrecpie.data

import retrofit2.Response
import romilp.foodrecpie.data.network.FoodRecipesApi
import romilp.foodrecpie.model.FoodRecipe
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi          //uvozimo podatke sa API-ja
) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }

    suspend fun searchRecipes(searchQuery: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.searchRecipes(searchQuery)
    }

}