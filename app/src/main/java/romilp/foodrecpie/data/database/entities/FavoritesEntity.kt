package romilp.foodrecpie.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import romilp.foodrecpie.model.Result
import romilp.foodrecpie.util.Constants.Companion.FAVORITE_RECIPES_TABLE


@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity
    (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)