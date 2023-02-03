package romilp.foodrecpie.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import romilp.foodrecpie.R
import romilp.foodrecpie.adapters.PagerAdapter
import romilp.foodrecpie.data.database.entities.FavoritesEntity
import romilp.foodrecpie.databinding.ActivityDetailsBinding
import romilp.foodrecpie.ui.fragments.ingredients.IngredientsFragment
import romilp.foodrecpie.ui.fragments.overview.OverviewFragment
import romilp.foodrecpie.util.Constants.Companion.RECIPE_RESULT_KEY
import romilp.foodrecpie.viewModels.MainViewModel

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val args by navArgs<DetailsActivityArgs>()
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityDetailsBinding

    private var recipeSaved = false
    private var savedRecipeId = 0

    private lateinit var menuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        setSupportActionBar(binding.toolBar)
        binding.toolBar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)       //omogucujemo da s evratimo na recpie actv <-

        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientsFragment())

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredients")

        val resultBundle = Bundle()
        resultBundle.putParcelable(RECIPE_RESULT_KEY, args.result)


        val pagerAdapter = PagerAdapter(
            resultBundle,
            fragments,
            this
        )

        binding.viewPager2.isUserInputEnabled = false
        binding.viewPager2.apply {
            adapter = pagerAdapter
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        menuItem = menu!!.findItem(R.id.save_to_favorites_menu)
        checkSavedRecipes(menuItem)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.save_to_favorites_menu && !recipeSaved) {
            saveToFavorite(item)                                                    //pozivamo metodu saveToFavorite
        } else if (item.itemId == R.id.save_to_favorites_menu && recipeSaved) {
            removeFromFavorites(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun checkSavedRecipes(menuItem: MenuItem) {                             //proveravo da li je vec sacuvan recept sa tim id
        mainViewModel.readFavoriteRecipes.observe(this) { favoritesEntity ->
            try {
                for (savedRecipe in favoritesEntity) {
                    if (savedRecipe.result.recipeId == args.result.recipeId) {
                        changeMenuItemColor(menuItem, R.color.yellow)
                        savedRecipeId = savedRecipe.id
                        recipeSaved = true
                    }
                }
            } catch (e: Exception) {
                Log.d("DetailsActivity", e.message.toString())
            }

        }
    }

    private fun saveToFavorite(item: MenuItem) {                    // logika za favourite recpie
        val favoritesEntity = FavoritesEntity(0, args.result)
        mainViewModel.insertFavoriteRecipe(favoritesEntity)
        changeMenuItemColor(item, R.color.yellow)
        showSnackBar("Recipe saved")
        recipeSaved = true
    }

    private fun removeFromFavorites(item: MenuItem) {
        val favoritesEntity = FavoritesEntity(savedRecipeId, args.result)
        mainViewModel.deleteFavoriteRecipe(favoritesEntity)
        changeMenuItemColor(item, R.color.white)                        //logika za boju zvezdice i uklanjanje itema iz favourite
        showSnackBar("Remove from Favorites")
        recipeSaved = false
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.detailsLayout, message, Snackbar.LENGTH_SHORT).setAction("Okay") {}
            .show()
    }

    private fun changeMenuItemColor(item: MenuItem, color: Int) {
        item.icon?.setTint(ContextCompat.getColor(this, color))
    }

    override fun onDestroy() {
        super.onDestroy()
        changeMenuItemColor(menuItem, R.color.white)
    }
}