package com.example.aula1.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aula1.domain.mapper.toModel
import com.example.aula1.domain.model.SampleModel
import com.example.aula1.domain.repository.SampleRepository
import com.example.aula1.networking.models.SampleResponse
import com.example.aula1.networking.service.Resource
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

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    private val callObserver: Observer<Resource<List<SampleResponse>>> =
        Observer { response ->  processResponse(response)}
    //setando observador


    private suspend fun callObserverAPI() {
        //suspend function que observa se houve alguma mudança
        repository.getSampleData().observeForever { callObserver.onChanged(it) }
    }

    fun triggerLiveData() {
        //função que seta o liveData
        _liveData.value = "LiveData"
    }

    fun triggerStateFlow() {
        //função que seta o stateFlow
        _stateFlow.value = "StateFlow"
    }

    fun triggerFlow(): Flow<String> {
        //função que seta o Flow para se repetir 3 vezes
        return flow {
            repeat(3) {
                emit("Item $it")
                delay(1000L)
            }
        }
    }

    fun triggerSharedFlow() {
        //função que seta o sharedFlow
        viewModelScope.launch {
            _sharedFlow.emit("SharedFlow")
        }
    }

    fun getSampleData() {
        //realizamos a chamada na camada de repository e adicionamos o observador
        viewModelScope.launch {
            callObserverAPI()
            repository.getSampleData()
        }
    }

    private fun processResponse(response: Resource<List<SampleResponse>>?) {
        //Aqui iremos processar a response de acordo com o retorno na camada de Repository
        when(response?.status) {
            Resource.Status.SUCCESS -> {
                setLoading(false)
                response.data?.let { sampleDataOnSuccess(it) }
            }
            Resource.Status.ERROR -> {
                setLoading(false)
                response.apiError?.let { sampleDataOnError(it) }
            }
            Resource.Status.LOADING -> {
                setLoading(true)
            }
            null -> TODO()
        }
    }

    private fun sampleDataOnSuccess(resultResponse: List<SampleResponse>){
        //tratativa de sucesso
        _sampleData.value = resultResponse.toModel()
    }

    private fun sampleDataOnError(message: String){
        //tratativa de erro
        _sampleError.value = ErrorModel(
            title = "Erro na chamada de api",
            message = message,
            errorCode = "0001"
        )
    }

    fun setLoading(isStateLoading: Boolean) {
        //tratativa de loading
        isLoading.value = isStateLoading
    }
}