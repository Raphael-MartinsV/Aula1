package com.example.aula1.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class BaseViewModelTest {
    //ViewModel Base de test que seta as regras e cria funções abstratas que devem ser implementadas

    @get: Rule
    val rule = InstantTaskExecutorRule()

    @get: Rule
    var coroutineTestRule = CoroutineTestRule()

    @Before
    abstract fun setup()

    @Before
    abstract fun tearDown()
}