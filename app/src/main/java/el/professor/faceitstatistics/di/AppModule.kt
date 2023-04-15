package el.professor.faceitstatistics.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import el.professor.faceitstatistics.data.local.PlayerDatabase
import el.professor.faceitstatistics.data.remote.PlayerApi
import el.professor.faceitstatistics.data.repository.PlayerRepositoryImpl
import el.professor.faceitstatistics.domain.repository.PlayerRepository
import el.professor.faceitstatistics.presentation.player_details.PlayerDetailsViewModel
import el.professor.faceitstatistics.presentation.players_list.PlayersListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

val appModule = module {
    fun providePlayerApi(): PlayerApi {
        return Retrofit.Builder()
            .baseUrl(PlayerApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    fun providePlayerDatabase(app: Application): PlayerDatabase {
        return Room.databaseBuilder(
            app,
            PlayerDatabase::class.java,
            "playerDb"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { providePlayerApi() }
    single { providePlayerDatabase(androidApplication()) }
}

val repositoryModule = module {
    single<PlayerRepository> { PlayerRepositoryImpl(get(), get()) }
}

val viewModelModule = module {
    viewModel { PlayersListViewModel(get()) }
    viewModel { PlayerDetailsViewModel(get(), get()) }
}