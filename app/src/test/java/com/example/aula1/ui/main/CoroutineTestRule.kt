package com.example.aula1.ui.main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

//Como as funções de teste JUnit em si não são funções de suspensão
// é necessário chamar um builder de corrotinas dentro dos testes para iniciar uma nova corrotina
@ExperimentalCoroutinesApi
class CoroutineTestRule(private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher())
    : TestWatcher() {
    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}