package romilp.foodrecpie.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import romilp.foodrecpie.model.FoodRecipe
import romilp.foodrecpie.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(var foodRecipe: FoodRecipe) { // sadrzace samo jedan red od food recipe i koristimo listu za recepte
    @PrimaryKey(autoGenerate = false)           // kada fecth jemo nove podatke sa API, menjamo podatke iz stare tab sa nomim podacima
    var id: Int = 0                             //kada aplikacija iscitava tabelu ispisuje nove rez
}