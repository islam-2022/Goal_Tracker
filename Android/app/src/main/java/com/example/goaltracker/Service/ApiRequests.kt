package com.example.goaltracker.Service
import com.example.goaltracker.Model.Evidence
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequest {
	@GET("/evidence")
	fun getEvidence(): Call<List<Evidence>>
}
