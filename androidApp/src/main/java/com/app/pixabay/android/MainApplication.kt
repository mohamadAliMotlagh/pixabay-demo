package com.app.pixabay.android

import android.app.Application
import initKoin
import org.koin.android.ext.koin.androidContext

class MainApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidContext(this@MainApplication)
        }
    }
}