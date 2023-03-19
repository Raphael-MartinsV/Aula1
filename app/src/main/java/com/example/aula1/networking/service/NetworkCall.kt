package com.example.aula1.networking.service

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class NetworkCall<T> {
    lateinit var call: Call<T>

    //função que realiza a chamada passando um callback que poderá ser de sucesso ou erro
    fun makeCall(call: Call<T>): MutableLiveData<Resource<T>> {
        this.call = call
        val callBackKt = CallBackKt<T>()
        callBackKt.result.value = Resource.loading(null)
        this.call.clone().enqueue(callBackKt)
        return callBackKt.result
    }

    //função callBack que seta um resultado baseado se o retornado foi de sucesso ou de erro
    class CallBackKt<T>: Callback<T> {
        var result: MutableLiveData<Resource<T>> = MutableLiveData()

        override fun onFailure(call: Call<T>, t: Throwable){
            result.value = Resource.error(t.message ?: "")
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            if(response.isSuccessful){
                result.value = Resource.success(response.body())
            } else {
                result.value = Resource.error("Error")
            }
        }
    }
}