package com.example.movieapplication

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.movieapplication.KoinModule.dataModule
import com.example.movieapplication.KoinModule.uiModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        Log.d("App", "Starting Koin initialization")
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    dataModule, uiModule
                )
            )
        }
        Log.d("App", "Koin initialization completed")

    }
    companion object {
        var context : Context? = null
    }

}