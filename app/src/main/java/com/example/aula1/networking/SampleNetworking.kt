package com.example.aula1.networking

import com.example.aula1.networking.models.SampleResponse
import retrofit2.Call
import retrofit2.http.GET

interface SampleNetworking {

    @GET("posts")
    fun getSampleData(): Call<List<SampleResponse>>
}