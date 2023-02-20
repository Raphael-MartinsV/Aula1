package com.example.aula1.networking.service

interface APIListener<T> {
    fun onSuccess(result:T)
    fun onFailure(message: String)
}