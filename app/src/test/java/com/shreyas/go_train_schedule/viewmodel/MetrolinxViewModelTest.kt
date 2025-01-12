package com.shreyas.go_train_schedule.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shreyas.go_train_schedule.models.MetrolinxResponse
import com.shreyas.go_train_schedule.repository.MetrolinxRepository
import com.shreyas.go_train_schedule.utils.DataProvider
import com.shreyas.go_train_schedule.utils.ResultWrapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MetrolinxViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: MetrolinxRepository
    private lateinit var viewModel: MetrolinxViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        repository = mockk()
        viewModel = MetrolinxViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchAllGoTrainsInfo success updates LiveData correctly`() = runTest {
        // Arrange
        val mockResponse = DataProvider.metroLinxResponse
        coEvery { repository.getAllGoTrainsServiceInfo() } returns flow {
            emit(ResultWrapper.LOADING(true))
            emit(ResultWrapper.LOADING(false))
            emit(ResultWrapper.SUCCESS(mockResponse))
        }

        viewModel.updateNetworkStatus(true)
        viewModel.fetchAllGoTrainsInfo()

        assertEquals(false, viewModel.isLoading.value)
        assertEquals(mockResponse, viewModel.metroLinxResponse.value)
        assertEquals(false, viewModel.isError.value)
    }

    @Test
    fun `fetchAllGoTrainsInfo failure updates LiveData correctly`() = runTest {
        val errorCode = "Error fetching train info"
        coEvery { repository.getAllGoTrainsServiceInfo() } returns flow {
            emit(ResultWrapper.LOADING(true))
            emit(ResultWrapper.LOADING(false))
            emit(ResultWrapper.FAILURE(errorCode))
        }

        viewModel.updateNetworkStatus(true)
        viewModel.fetchAllGoTrainsInfo()

        assertEquals(false, viewModel.isLoading.value)
        assertNull(viewModel.metroLinxResponse.value)
        assertEquals(true, viewModel.isError.value)
        assertEquals(errorCode, viewModel.metroLinxErrorResponse.value)
    }

    @Test
    fun `fetchAllGoTrainDeparturesFromUnion success updates LiveData correctly`() = runTest {
        // Arrange
        val mockResponse = DataProvider.metroLinxResponse2
        coEvery { repository.getAllGoTrainDeparturesFromUnion() } returns flow {
            emit(ResultWrapper.LOADING(true))
            emit(ResultWrapper.LOADING(false))
            emit(ResultWrapper.SUCCESS(mockResponse))
        }

        viewModel.updateNetworkStatus(true)
        viewModel.fetchAllGoTrainDeparturesFromUnion()

        assertEquals(false, viewModel.isLoading.value)
        assertEquals(mockResponse, viewModel.metroLinxResponse.value)
        assertEquals(false, viewModel.isError.value)
    }

    @Test
    fun `fetchAllGoTrainDeparturesFromUnion failure updates LiveData correctly`() = runTest {
        val errorCode = "Error fetching departures"
        coEvery { repository.getAllGoTrainDeparturesFromUnion() } returns flow {
            emit(ResultWrapper.LOADING(true))
            emit(ResultWrapper.LOADING(false))
            emit(ResultWrapper.FAILURE(errorCode))
        }

        viewModel.updateNetworkStatus(true)
        viewModel.fetchAllGoTrainDeparturesFromUnion()

        assertEquals(false, viewModel.isLoading.value)
        assertNull(viewModel.metroLinxResponse.value)
        assertEquals(true, viewModel.isError.value)
        assertEquals(errorCode, viewModel.metroLinxErrorResponse.value)
    }

    @Test
    fun `fetchAllGoTrainStops success updates LiveData correctly`() = runTest {
        // Arrange
        val mockResponse = DataProvider.metroLinxResponse3
        coEvery { repository.getAllGoTrainStops() } returns flow {
            emit(ResultWrapper.LOADING(true))
            emit(ResultWrapper.LOADING(false))
            emit(ResultWrapper.SUCCESS(mockResponse))
        }

        viewModel.updateNetworkStatus(true)
        viewModel.fetchAllGoTrainStops()

        assertEquals(false, viewModel.isLoading.value)
        assertEquals(false, viewModel.isError.value)
        assertEquals(mockResponse.stations?.station, viewModel.stationList)
    }

    @Test
    fun `fetchAllGoTrainStops failure updates LiveData correctly`() = runTest {
        val errorCode = "Error fetching stops"
        coEvery { repository.getAllGoTrainStops() } returns flow {
            emit(ResultWrapper.LOADING(true))
            emit(ResultWrapper.LOADING(false))
            emit(ResultWrapper.FAILURE(errorCode))
        }

        viewModel.updateNetworkStatus(true)
        viewModel.fetchAllGoTrainStops()

        assertEquals(false, viewModel.isLoading.value)
        assertNull(viewModel.metroLinxResponse.value)
        assertEquals(true, viewModel.isError.value)
        assertEquals(errorCode, viewModel.metroLinxErrorResponse.value)
    }
}