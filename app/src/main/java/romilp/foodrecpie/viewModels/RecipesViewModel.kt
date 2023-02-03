package romilp.foodrecpie.viewModels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import romilp.foodrecpie.util.Constants.Companion.API_KEY
import romilp.foodrecpie.util.Constants.Companion.DEFAULT_DIET_TYPE
import romilp.foodrecpie.util.Constants.Companion.DEFAULT_MEAL_TYPE
import romilp.foodrecpie.util.Constants.Companion.DEFAULT_RECIPES_NUMBER
import romilp.foodrecpie.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import romilp.foodrecpie.util.Constants.Companion.QUERY_API_KEY
import romilp.foodrecpie.util.Constants.Companion.QUERY_DIET
import romilp.foodrecpie.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import romilp.foodrecpie.util.Constants.Companion.QUERY_NUMBER
import romilp.foodrecpie.util.Constants.Companion.QUERY_SEARCH
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {


    var networkStatus = false
    var backOnline = false


    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }

    fun applySearchQuery(searchQuery: String): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[QUERY_SEARCH] = searchQuery
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }


    fun showNetworkStatus() {
        if (!networkStatus) {
            Toast.makeText(getApplication(), "No Internet Connection.", Toast.LENGTH_SHORT).show()
        } else if (networkStatus) {
            if (backOnline) {
                Toast.makeText(getApplication(), "We're back online.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
