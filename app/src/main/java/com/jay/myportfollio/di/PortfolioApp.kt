package com.jay.myportfollio.di

import android.app.Application
import android.util.Log
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PortfolioApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Log.d("KoinSetup", "Initializing Koin...")
        startKoin {
            androidContext(this@PortfolioApp)
            androidLogger(Level.DEBUG)
            modules(appModule)
        }
    }
}