package com.example.aula1.domain.mapper

import com.example.aula1.domain.model.SampleModel
import com.example.aula1.networking.models.SampleResponse
import com.example.aula1.ui.model.setInfoHelper
import com.example.aula1.ui.utils.sampleConst1

fun List<SampleResponse>.toModel(): List<SampleModel>{
    return this.map { item->
        SampleModel(
            userId = item.userId ?: 0,
            id = item.id ?: 0,
            title = item.title.orEmpty(),
            body = item.body.orEmpty(),
            infoHelper = setInfoHelper()[sampleConst1]
        )
    }
}