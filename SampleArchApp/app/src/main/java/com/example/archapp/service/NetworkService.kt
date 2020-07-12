package com.example.archapp.service

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class NetworkService {

    val apiTest : ApiTestService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiTest = retrofit.create(ApiTestService::class.java)
    }

    companion object {
        const val BASE_URL = "https://postman-echo.com/"
    }

    interface ApiTestService {
        @GET("/get?foo1=bar1&foo2=bar2")
        fun testApiWithQueryParams() : Call<JsonObject>
    }
}