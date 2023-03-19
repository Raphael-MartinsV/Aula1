package com.example.aula1.di

import com.example.aula1.domain.repository.SampleRepository
import com.example.aula1.domain.repository.impl.SampleRepositoryImpl
import com.example.aula1.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

//setando os modulos que serão vinculado a biblioteca do Koin
val mainModule = module {
    // instância é fornecida automaticamente de acordo com o escopo necessário
    // um Singleton ou Factory (instância sempre nova)
    factory<SampleRepository> {
        SampleRepositoryImpl()
    }

    //vinculado a viewModel a instaância gerada do repository
    viewModel {
        MainViewModel( repository = get())
    }
}