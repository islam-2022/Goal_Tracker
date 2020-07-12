package com.example.archapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.archapp.service.DependencyInjectionService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DependencyInjectionService.init()
        setContentView(R.layout.activity_main)
    }
}