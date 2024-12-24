package com.jay.myportfollio.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PortfolioApp : Application() {
    override fun onCreate() {
        super.onCreate()


        startKoin {
            modules(appModule)
            androidContext(this@PortfolioApp)
            androidLogger(Level.DEBUG)
        }
    }
}