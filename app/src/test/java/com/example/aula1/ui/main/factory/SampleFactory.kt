package com.example.aula1.ui.main.factory

import com.example.aula1.domain.model.SampleModel
import com.example.aula1.networking.models.SampleResponse
import com.example.aula1.ui.model.ErrorModel
import com.example.aula1.ui.model.setInfoHelper
import com.example.aula1.ui.utils.sampleConst1

object SampleFactory {

    fun createSampleData() = listOf(
        SampleModel(
            userId = 1,
            id = 1,
            title = "title",
            body = "test",
            infoHelper = setInfoHelper()[sampleConst1]
        ),
        SampleModel(
            userId = 2,
            id = 2,
            title = "title",
            body = "test",
            infoHelper = setInfoHelper()[sampleConst1]
        )
    )

    fun createSampleResponse() = listOf(
        SampleResponse(
            userId = 1,
            id = 1,
            title = "title",
            body = "test"
        ),
        SampleResponse(
            userId = 2,
            id = 2,
            title = "title",
            body = "test"
        )
    )

    fun createSampleError() = ErrorModel(
        title = "Erro na chamada de api",
        message = "Error",
        errorCode = "0001"
    )
}