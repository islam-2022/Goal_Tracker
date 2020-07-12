package com.example.archapp.service

import org.koin.core.context.startKoin
import org.koin.dsl.module

object DependencyInjectionService {

    fun init() {
        startKoin {
            masterModule
        }
    }

    private val masterModule = module {
        single { NetworkService() }
    }
}