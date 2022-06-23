package com.example.recordsapi

import android.app.Application
import com.example.recordsapi.DI.ApplicationModule
import com.example.recordsapi.DI.DaggerSongsComponent
import com.example.recordsapi.DI.SongsComponent


class SongsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        component = DaggerSongsComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    companion object {
    lateinit var component: SongsComponent
    }
}