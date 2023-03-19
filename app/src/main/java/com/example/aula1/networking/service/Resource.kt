package com.example.aula1.networking.service

class Resource<T> private constructor(val status: Status, val data: T?, val apiError: String?){

    //Estados possíveis da chamada à API
    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        //configurando a função que retorna o status de suceso
        // com os dados retornados pela chamada ao serviço
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }
        //configurando a função que retorna o status de erro
        // com a string da mensagem retornada pelo serviço
        fun <T> error(apiError: String?): Resource<T> {
            return Resource(Status.ERROR, null, apiError)
        }
        //configurando a função que retorna o status de loading
        //ela ainda não terá retornado nada pois a requisição não foi finalizada
        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}