package com.example.aula1.domain.repository

import com.example.aula1.networking.models.SampleResponse
import com.example.aula1.networking.service.APIListener
import com.example.aula1.networking.service.RetrofitClient
import com.example.aula1.networking.SampleNetworking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SampleRepository {

    private val remote = RetrofitClient.createPostService(SampleNetworking::class.java)

    private fun <T> executeCall(call: Call<T>, listener: APIListener<T>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.code() == 200) {
                    response.body()?.let { listener.onSuccess(it) }
                } else {
                    listener.onFailure(response.errorBody()?.string() ?: "Error")
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val failure = t.message
                listener.onFailure(failure.orEmpty())
            }
        })
    }

    fun getSampleData(listener: APIListener<List<SampleResponse>>) {
        executeCall(remote.getSampleData(), listener)
    }
}