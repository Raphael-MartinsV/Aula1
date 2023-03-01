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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val repository: SampleRepository) : ViewModel() {

    private val _liveData = MutableLiveData("Hello World")
    val liveData: LiveData<String> = _liveData

    private val _stateFlow = MutableStateFlow("Hello World")
    val stateFlow = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    private val _sampleData = MutableLiveData<List<SampleModel>>()
    val sampleData: LiveData<List<SampleModel>> = _sampleData

    private val _sampleError = MutableLiveData<ErrorModel>()
    val sampleError: LiveData<ErrorModel> = _sampleError

    fun triggerLiveData() {
        _liveData.value = "LiveData"
    }

    fun triggerStateFlow() {
        _stateFlow.value = "StateFlow"
    }

    fun triggerFlow(): Flow<String> {
        return flow {
            repeat(3) {
                emit("Item $it")
                delay(1000L)
            }
        }
    }

    fun triggerSharedFlow() {
        viewModelScope.launch {
            _sharedFlow.emit("SharedFlow")
        }
    }

    fun getSampleData() {
        viewModelScope.launch {
            repository.getSampleData(object : APIListener<List<SampleResponse>> {
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