package dev.zaqueu.eventfinder

import android.app.Application
import dev.zaqueu.eventfinder.common.data.di.DataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EventFinderApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EventFinderApplication)
        }
        DataModule.load()
    }
}
