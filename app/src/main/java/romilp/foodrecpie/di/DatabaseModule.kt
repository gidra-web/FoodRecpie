package romilp.foodrecpie.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import romilp.foodrecpie.data.database.RecipesDatabase
import romilp.foodrecpie.util.Constants.Companion.DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides               //doadatak koji je 3rd party i omogucava komunikaciju Room lib
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, RecipesDatabase::class.java, DATABASE_NAME).build()  //bildujemo Room bazu
    //omogucimo context iz hilt biblioteke kako bi ijektovali podatke odmah
    //contex - iz provideDatabase fukcije, definisana baza RecipesDatabase ,
    // i DATABASE_NAME-constatnta za recpie database, ime baze koju upotrebljavamo

    @Singleton
    @Provides
    fun provideDao(database: RecipesDatabase) = database.recipesDao()
    //iz RacpiesDatabes.kt pozivamo metodu recipesDao() da bi uvezili podatke u bazu

    //implemenitiramo apstraktnu metodu koja omogucava da database kroz recipesDao
}