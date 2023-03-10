package com.example.aula1.domain.model

import com.example.aula1.ui.model.SampleHelper
import kotlinx.parcelize.Parcelize

@Parcelize
data class SampleModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val infoHelper: SampleHelper? = null
)
