package com.example.archapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.archapp.service.DependencyInjectionService
import com.example.archapp.service.NetworkService
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), KoinComponent {

    val networkService : NetworkService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkService.apiTest.testApiWithQueryParams().enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                text.text = t.message ?: "error"
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                text.text = response.body()?.toString() ?: ""
            }
        })
    }


}