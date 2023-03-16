package com.example.aula1.domain.repository.impl

import androidx.lifecycle.LiveData
import com.example.aula1.domain.repository.SampleRepository
import com.example.aula1.networking.models.SampleResponse
import com.example.aula1.networking.service.RetrofitClient
import com.example.aula1.networking.SampleNetworking
import com.example.aula1.networking.service.NetworkCall
import com.example.aula1.networking.service.Resource

class SampleRepositoryImpl: SampleRepository {

    private val remote = RetrofitClient.createPostService(SampleNetworking::class.java)

    override suspend fun getSampleData(): LiveData<Resource<List<SampleResponse>>> {
        val getSampleCall = NetworkCall<List<SampleResponse>>()
        return getSampleCall.makeCall(remote.getSampleData())
    }
}