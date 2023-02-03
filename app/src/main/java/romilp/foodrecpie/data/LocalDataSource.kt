package romilp.foodrecpie.data

import kotlinx.coroutines.flow.Flow
import romilp.foodrecpie.data.database.RecipesDAO
import romilp.foodrecpie.data.database.entities.FavoritesEntity
import romilp.foodrecpie.data.database.entities.RecipesEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDAO: RecipesDAO              //inject recpies u dao
) {
    fun readRecipes(): Flow<List<RecipesEntity>> {
        return recipesDAO.readRecipes()
    }

    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>> {
        return recipesDAO.readFavoriteRecipes()
    }

    suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDAO.insertRecipes(recipesEntity)
    }

    suspend fun insertFavoriteRecipes(favoritesEntity: FavoritesEntity) {
        recipesDAO.insertFavoriteRecipe(favoritesEntity)
    }

    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity) {
        recipesDAO.deleteFavoriteRecipe(favoritesEntity)
    }

    suspend fun deleteAllFavoriteRecipes() {
        recipesDAO.deleteAllFavoriteRecipes()
    }
}