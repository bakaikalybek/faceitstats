package el.professor.faceitstatistics

import android.app.Application
import el.professor.faceitstatistics.di.appModule
import el.professor.faceitstatistics.di.repositoryModule
import el.professor.faceitstatistics.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule, repositoryModule, viewModelModule)
        }
    }
}