package com.example.archapp.service

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

object DependencyInjectionService {

    fun init(context: Context) {
        startKoin {
            androidLogger()
            androidContext(context)
            modules(masterModule)
        }
    }

    private val masterModule = module {
        single { NetworkService() }
    }
}