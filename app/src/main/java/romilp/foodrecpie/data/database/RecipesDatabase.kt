package romilp.foodrecpie.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import romilp.foodrecpie.data.database.entities.FavoritesEntity
import romilp.foodrecpie.data.database.entities.RecipesEntity

@Database(          //baza recepata
    entities = [RecipesEntity::class, FavoritesEntity::class],      // 2 tabele
    version = 1,
    exportSchema = false                //govorimo Room da ne eksportuje databazu u novi folder
)

@TypeConverters(RecipesTypeConverter::class)            //pozivamo konverziju
abstract class RecipesDatabase : RoomDatabase() {

    abstract fun recipesDao(): RecipesDAO           //pozivamo doa interfejs
}