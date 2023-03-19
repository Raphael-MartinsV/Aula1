package com.example.aula1.networking

import com.example.aula1.networking.models.SampleResponse
import retrofit2.Call
import retrofit2.http.GET

interface SampleNetworking {

    //Método http com o endpoint da chamada e o tipo de retorno esperado
    @GET("posts")
    fun getSampleData(): Call<List<SampleResponse>>
}