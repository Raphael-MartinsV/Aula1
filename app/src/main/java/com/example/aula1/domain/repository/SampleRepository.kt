package com.example.aula1.domain.repository

import androidx.lifecycle.LiveData
import com.example.aula1.networking.models.SampleResponse
import com.example.aula1.networking.service.Resource

interface SampleRepository {
    // interface com a função suspensa que irá retornar o LiveData
    // com o modelo solicitado
    suspend fun getSampleData(): LiveData<Resource<List<SampleResponse>>>
}