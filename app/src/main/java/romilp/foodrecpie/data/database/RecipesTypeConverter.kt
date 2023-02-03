package romilp.foodrecpie.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import romilp.foodrecpie.model.FoodRecipe
import romilp.foodrecpie.model.Result

class RecipesTypeConverter {
    var gson = Gson()

    @TypeConverter
    fun foodRecipesToString(foodRecipe: FoodRecipe): String {
        return gson.toJson(foodRecipe)                          //konverrtujemo foodrecipes iz json objekata u string
    }

    @TypeConverter
    fun stringToFoodRecipes(data: String): FoodRecipe {         //obrnuta konverzija
        var listType = object : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun resultToString(result: Result): String {
        return gson.toJson(result)
    }

    @TypeConverter
    fun stringToResult(data: String): Result {
        val listType = object : TypeToken<Result>() {}.type
        return gson.fromJson(data, listType)
    }
}