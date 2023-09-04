package com.owori.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OworiApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@OworiApplication)
            modules(appModules)
        }
    }
}