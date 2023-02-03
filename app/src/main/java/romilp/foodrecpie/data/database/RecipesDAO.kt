package romilp.foodrecpie.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import romilp.foodrecpie.data.database.entities.FavoritesEntity
import romilp.foodrecpie.data.database.entities.RecipesEntity

@Dao
interface RecipesDAO {      //data acces object

    @Insert(onConflict = OnConflictStrategy.REPLACE)                //replace stare podateke
    suspend fun insertRecipes(recipesEntity: RecipesEntity)         //za insertovanje recepatap preko korutina

    @Insert(onConflict = OnConflictStrategy.REPLACE)                //replace stare podateke
    suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity)

    @Query("SELECT * FROM recipes_table ORDER BY id ASC")           //upit koji koristimo za selektovanje recepata
    fun readRecipes(): Flow<List<RecipesEntity>>

    @Query("SELECT * FROM favorite_recipes_table ORDER BY id ASC")
    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>>

    @Delete
    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity)

    @Query("DELETE FROM favorite_recipes_table")
    suspend fun deleteAllFavoriteRecipes()


}