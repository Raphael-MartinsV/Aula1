package com.example.aula1.domain.repository

import androidx.lifecycle.LiveData
import com.example.aula1.networking.models.SampleResponse
import com.example.aula1.networking.service.Resource

interface SampleRepository {
    suspend fun getSampleData(): LiveData<Resource<List<SampleResponse>>>
}