package romilp.foodrecpie.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) {
    val remote = remoteDataSource
    val local = localDataSource         //pozivamo preko MainView modela var local koja ima vrednost localDataSource
                                        // i sluzi za kesiranje podataka koje dobija iz API-ja (device offline)
                                        // logika u MainViewModel-u
}