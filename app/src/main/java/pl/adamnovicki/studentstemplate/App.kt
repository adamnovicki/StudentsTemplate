package pl.adamnovicki.studentstemplate

import android.app.Application
import android.util.Log
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import java.util.logging.Logger

/**
 * Created by adamnowicki on 2019-09-30.
 */
class App : Application() {

    var listofModules =
        listOf(
            MainModule.mainModule
        )

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listofModules)

        }
    }
}