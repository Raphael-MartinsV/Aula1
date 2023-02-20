package com.example.aula1.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aula1.domain.mapper.toModel
import com.example.aula1.domain.model.SampleModel
import com.example.aula1.domain.repository.SampleRepository
import com.example.aula1.networking.models.SampleResponse
import com.example.aula1.networking.service.APIListener
import com.example.aula1.ui.model.ErrorModel
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val sampleRepository = SampleRepository()
    private val _sampleData = MutableLiveData<List<SampleModel>>()
    val sampleData: LiveData<List<SampleModel>> = _sampleData

    private val _sampleError = MutableLiveData<ErrorModel>()
    val sampleError: LiveData<ErrorModel> = _sampleError

    fun getSampleData() {
        viewModelScope.launch {
            sampleRepository.getSampleData(object : APIListener<List<SampleResponse>> {
                override fun onSuccess(result: List<SampleResponse>) {
                    _sampleData.value = result.toModel()
                }

                override fun onFailure(message: String) {
                    _sampleError.value = ErrorModel(
                        title = "Erro na chamada da API",
                        message = message,
                        errorCode = "0001"
                    )
                }
            }
            )
        }
    }
}