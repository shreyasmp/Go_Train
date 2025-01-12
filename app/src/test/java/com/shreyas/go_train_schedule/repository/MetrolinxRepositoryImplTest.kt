package com.shreyas.go_train_schedule.repository

import com.shreyas.go_train_schedule.api.GoApi
import com.shreyas.go_train_schedule.utils.DataProvider
import com.shreyas.go_train_schedule.utils.ResultWrapper
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class MetrolinxRepositoryImplTest {

    private lateinit var repository: MetrolinxRepositoryImpl
    private val service: GoApi = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        repository = MetrolinxRepositoryImpl(service)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getAllGoTrainsServiceInfo success`() = runTest {
        // Arrange
        val mockResponse = DataProvider.metroLinxResponse
        coEvery { service.getAllGoTrainServicesAtGlance() } returns Response.success(mockResponse)

        // Act
        val result = repository.getAllGoTrainsServiceInfo().toList()

        // Assert
        assertEquals(ResultWrapper.LOADING(true), result[0])
        assertEquals(ResultWrapper.LOADING(false), result[1])
        assertEquals(ResultWrapper.SUCCESS(mockResponse), result[2])
    }

    @Test
    fun `getAllGoTrainsServiceInfo failure`() = runTest {

        val errorResponseBody = mockk<ResponseBody> {
            every { contentType() } returns "application/json".toMediaType()
            every { contentLength() } returns 0L
        }

        // Arrange
        coEvery { service.getAllGoTrainServicesAtGlance() } returns Response.error(
            404,
            errorResponseBody
        )

        // Act
        val result = repository.getAllGoTrainsServiceInfo().toList()

        // Assert
        assertEquals(ResultWrapper.LOADING(true), result[0])
        assertEquals(ResultWrapper.LOADING(false), result[1])
        assertEquals(ResultWrapper.FAILURE("Response.error()"), result[2])
    }

    @Test
    fun `getAllGoTrainDeparturesFromUnion success`() = runTest {
        // Arrange
        val mockResponse = DataProvider.metroLinxResponse2
        coEvery { service.getAllGoTrainUnionDepartures() } returns Response.success(mockResponse)

        // Act
        val result = repository.getAllGoTrainDeparturesFromUnion().toList()

        // Assert
        assertEquals(ResultWrapper.LOADING(true), result[0])
        assertEquals(ResultWrapper.LOADING(false), result[1])
        assertEquals(ResultWrapper.SUCCESS(mockResponse), result[2])
    }

    @Test
    fun `getAllGoTrainDeparturesFromUnion failure`() = runTest {

        val errorResponseBody = mockk<ResponseBody> {
            every { contentType() } returns "application/json".toMediaType()
            every { contentLength() } returns 0L
        }

        // Arrange
        coEvery { service.getAllTrainStops() } returns Response.error(
            404,
            errorResponseBody
        )

        // Act
        val result = repository.getAllGoTrainStops().toList()

        // Assert
        assertEquals(ResultWrapper.LOADING(true), result[0])
        assertEquals(ResultWrapper.LOADING(false), result[1])
        assertEquals(ResultWrapper.FAILURE("Response.error()"), result[2])
    }

    @Test
    fun `getAllGoTrainStops success`() = runTest {
        // Arrange
        val mockResponse = DataProvider.metroLinxResponse3
        coEvery { service.getAllTrainStops() } returns Response.success(mockResponse)

        // Act
        val result = repository.getAllGoTrainStops().toList()

        // Assert
        assertEquals(ResultWrapper.LOADING(true), result[0])
        assertEquals(ResultWrapper.LOADING(false), result[1])
        assertEquals(ResultWrapper.SUCCESS(mockResponse), result[2])
    }

    @Test
    fun `getAllGoTrainStops failure`() = runTest {

        val errorResponseBody = mockk<ResponseBody> {
            every { contentType() } returns "application/json".toMediaType()
            every { contentLength() } returns 0L
        }

        // Arrange
        coEvery { service.getAllGoTrainUnionDepartures() } returns Response.error(
            404,
            errorResponseBody
        )

        // Act
        val result = repository.getAllGoTrainDeparturesFromUnion().toList()

        // Assert
        assertEquals(ResultWrapper.LOADING(true), result[0])
        assertEquals(ResultWrapper.LOADING(false), result[1])
        assertEquals(ResultWrapper.FAILURE("Response.error()"), result[2])
    }
}