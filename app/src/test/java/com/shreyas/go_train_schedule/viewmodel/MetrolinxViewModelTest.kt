package com.shreyas.go_train_schedule.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.shreyas.go_train_schedule.models.MetrolinxResponse
import com.shreyas.go_train_schedule.repository.MetrolinxRepository
import com.shreyas.go_train_schedule.utils.DataProvider
import com.shreyas.go_train_schedule.utils.ResultWrapper
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MetrolinxViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    @MockK
    private lateinit var repository: MetrolinxRepository

    private lateinit var viewModel: MetrolinxViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = MetrolinxViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Ignore("Needs Fix")
    @Test
    fun `fetchAllGoTrainsInfo should update live data on success`() = runTest {
        val response = DataProvider.metroLinxResponse // Create a sample response
        val flow = flow {
            emit(ResultWrapper.LOADING(false))
            emit(ResultWrapper.SUCCESS(response))
        }

        coEvery { repository.getAllGoTrainsServiceInfo() } returns flow

        val observer = mockk<Observer<MetrolinxResponse?>>(relaxed = true)
        viewModel.metroLinxResponse.observeForever(observer)

        viewModel.fetchAllGoTrainsInfo()

        testDispatcher.scheduler.advanceUntilIdle()

        verify { observer.onChanged(response) }
    }

    @Ignore("Needs Fix")
    @Test
    fun `fetchAllGoTrainsInfo should update error live data on failure`() = runTest {
        val errorCode = "404"
        val flow = flow {
            emit(ResultWrapper.LOADING(false))
            emit(ResultWrapper.FAILURE(errorCode))
        }

        coEvery { repository.getAllGoTrainsServiceInfo() } returns flow

        val observer = mockk<Observer<String?>>(relaxed = true)
        viewModel.metroLinxErrorResponse.observeForever(observer)

        viewModel.fetchAllGoTrainsInfo()

        testDispatcher.scheduler.advanceUntilIdle()

        verify { observer.onChanged(errorCode) }
    }
}