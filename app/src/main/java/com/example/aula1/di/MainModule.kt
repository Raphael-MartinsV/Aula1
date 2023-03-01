package com.example.aula1.di

import com.example.aula1.domain.repository.SampleRepository
import com.example.aula1.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainModule = module {
    factory {
        SampleRepository()
    }

    viewModel {
        MainViewModel( repository = get())
    }
}