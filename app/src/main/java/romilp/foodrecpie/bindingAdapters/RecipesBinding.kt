package romilp.foodrecpie.bindingAdapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import romilp.foodrecpie.data.database.entities.RecipesEntity
import romilp.foodrecpie.model.FoodRecipe
import romilp.foodrecpie.util.NetworkResult

class RecipesBinding {

    companion object {

        @BindingAdapter("readApiResponse", "readDatabase", requireAll = true)           //api, database
        @JvmStatic
        fun handleReadDataErrors(
            view: View,
            apiResponse: NetworkResult<FoodRecipe>?,
            database: List<RecipesEntity>?
        ) {
            when (view) {
                is ImageView -> {               //logika za prikaz slika i teksta
                    view.isVisible = apiResponse is NetworkResult.Error && database.isNullOrEmpty()
                }
                is TextView -> {
                    view.isVisible = apiResponse is NetworkResult.Error && database.isNullOrEmpty()
                    view.text = apiResponse?.message.toString()
                }
            }
        }
    }
}