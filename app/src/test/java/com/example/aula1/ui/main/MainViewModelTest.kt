package com.example.aula1.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.aula1.domain.model.SampleModel
import com.example.aula1.domain.repository.SampleRepository
import com.example.aula1.networking.models.SampleResponse
import com.example.aula1.networking.service.Resource
import com.example.aula1.ui.main.factory.SampleFactory.createSampleData
import com.example.aula1.ui.main.factory.SampleFactory.createSampleError
import com.example.aula1.ui.main.factory.SampleFactory.createSampleResponse
import com.example.aula1.ui.model.ErrorModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MainViewModelTest : BaseViewModelTest() {

    private val sampleRepository: SampleRepository = mockk()
    private lateinit var mainViewModel: MainViewModel

    override fun setup() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(sampleRepository)
    }

    override fun tearDown() = unmockkAll()

    @Test
    fun `When SampleRepository returns success and getSampleData returns success`() =
        runBlocking {
            val listSampleModel = createSampleData()
            val listResponse = createSampleResponse()
            val mutableSampleData = MutableLiveData(Resource.success(listResponse))
            val sampleData: LiveData<Resource<List<SampleResponse>>> = mutableSampleData
            coEvery { sampleRepository.getSampleData() } returns sampleData
            mainViewModel.getSampleData()

            val result: LiveData<List<SampleModel>> = MutableLiveData(listSampleModel)

            assertEquals(result.value, mainViewModel.sampleData.value)
        }

    @Test
    fun `When SampleRepository returns success but getSampleData should be error`() =
        runBlocking {
            val sampleError = createSampleError()
            val mutableSampleData = MutableLiveData(Resource.error<List<SampleResponse>>("Error"))
            val sampleErrorData: LiveData<Resource<List<SampleResponse>>> = mutableSampleData
            coEvery { sampleRepository.getSampleData() } returns sampleErrorData
            mainViewModel.getSampleData()

            val result: LiveData<ErrorModel> = MutableLiveData(sampleError)

            assertEquals(result.value, mainViewModel.sampleError.value)

        }

    @Test
    fun `When setLoading returns true and liveData shoud be true`() =
        runBlocking {
            mainViewModel.setLoading(true)
            assertTrue(
                mainViewModel.isLoading.value == true
            )
        }

    @Test
    fun `When setLoading returns false and liveData shoud be false`() =
        runBlocking {
            mainViewModel.setLoading(false)
            assertTrue(
                mainViewModel.isLoading.value == false
            )
        }

    @Test
    fun `When triggerFlow has three sentences and succeed`() =
        runBlocking {
            val test = mainViewModel.triggerFlow().count()

            val countExpected = 3

            assertEquals(test, countExpected)
        }

    @Test
    fun `When triggerFlow fetch sentence and succeed`() =
        runBlocking {
            val test = mainViewModel.triggerFlow().toList()
            val valueExpected = listOf("Item 0", "Item 1", "Item 2")

            assertEquals(test, valueExpected)
        }


    @Test
    fun `When triggerSharedFlow returns a string and sharedFlow be not null`() =
        runBlocking {
            val job = launch { mainViewModel.triggerSharedFlow() }
            val test = "SharedFlow"

            assertEquals(
                mainViewModel.sharedFlow.first(), test
            )

            job.cancel()
        }

}