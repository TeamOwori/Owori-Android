package com.owori.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OworiApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}