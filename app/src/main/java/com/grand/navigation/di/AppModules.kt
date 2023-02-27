package com.grand.navigation.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppModule : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppModule)
            modules(listOf(
                viewModule,
                repo,
                api,
                onBoarding
            ))
        }
    }
}