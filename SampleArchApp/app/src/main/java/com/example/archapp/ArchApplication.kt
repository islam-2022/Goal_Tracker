package com.example.archapp

import android.app.Application
import com.example.archapp.service.DependencyInjectionService

class ArchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DependencyInjectionService.init(this)
    }
}