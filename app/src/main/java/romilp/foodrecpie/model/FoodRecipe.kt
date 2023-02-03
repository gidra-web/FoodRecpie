package romilp.foodrecpie.model

import com.google.gson.annotations.SerializedName

data class FoodRecipe(
    @SerializedName("results")
    val results: List<Result>           //listu recepata
)