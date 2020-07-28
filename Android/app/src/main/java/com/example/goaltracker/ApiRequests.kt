package com.example.goaltracker
import com.example.goaltracker.Model.Evidence
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequest {
	@GET("/evidence")
	fun getEvidence(): Call<List<Evidence>>
}
