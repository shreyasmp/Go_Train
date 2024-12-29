package com.shreyas.go_train_schedule.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shreyas.go_train_schedule.repository.MetrolinxRepository
import com.shreyas.go_train_schedule.utils.DataProvider
import com.shreyas.go_train_schedule.utils.ResultWrapper
import io.mockk.clearAllMocks
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MetrolinxViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: MetrolinxRepository
    private lateinit var viewModel: MetrolinxViewModel

    private val testDispatcher = Dispatchers.Unconfined

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk(relaxed = true)
        viewModel = MetrolinxViewModel(repository)
        clearMocks(repository)
    }

    @Test
    fun `fetchAllGoTrainsInfo success updates LiveData correctly`() = runTest {
        // Arrange
        val mockResponse = DataProvider.metroLinxResponse
        coEvery { repository.getAllGoTrainsServiceInfo() } returns flow {
            emit(ResultWrapper.LOADING(false))
            emit(ResultWrapper.SUCCESS(mockResponse))
        }

        // Act
        viewModel.fetchAllGoTrainsInfo()

        // Assert
        assertEquals(false, viewModel.isLoading.value)
        assertEquals(false, viewModel.isLoading.value)
        assertEquals(mockResponse, viewModel.metroLinxResponse.value)
        assertEquals(false, viewModel.isError.value)
    }

    @Test
    fun `fetchAllGoTrainsInfo failure updates LiveData correctly`() = runTest {
        // Arrange
        val errorCode = "Error fetching train info"
        coEvery { repository.getAllGoTrainsServiceInfo() } returns flow {
            emit(ResultWrapper.LOADING(false))
            emit(ResultWrapper.FAILURE(errorCode))
        }

        // Act
        viewModel.fetchAllGoTrainsInfo()

        // Assert
        assertEquals(false, viewModel.isLoading.value)
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
            emit(ResultWrapper.LOADING(false))
            emit(ResultWrapper.SUCCESS(mockResponse))
        }

        // Act
        viewModel.fetchAllGoTrainDeparturesFromUnion()

        // Assert
        assertEquals(false, viewModel.isLoading.value)
        assertEquals(false, viewModel.isLoading.value)
        assertEquals(mockResponse, viewModel.metroLinxResponse.value)
        assertEquals(false, viewModel.isError.value)
    }

    @Test
    fun `fetchAllGoTrainDeparturesFromUnion failure updates LiveData correctly`() = runTest {
        // Arrange
        val errorCode = "Error fetching departures"
        coEvery { repository.getAllGoTrainDeparturesFromUnion() } returns flow {
            emit(ResultWrapper.LOADING(false))
            emit(ResultWrapper.FAILURE(errorCode))
        }

        // Act
        viewModel.fetchAllGoTrainDeparturesFromUnion()

        // Assert
        assertEquals(false, viewModel.isLoading.value)
        assertEquals(false, viewModel.isLoading.value)
        assertNull(viewModel.metroLinxResponse.value)
        assertEquals(true, viewModel.isError.value)
        assertEquals(errorCode, viewModel.metroLinxErrorResponse.value)
    }

    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }
}