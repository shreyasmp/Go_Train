package com.shreyas.go_train_schedule.repository

import android.util.Log
import com.shreyas.go_train_schedule.api.GoApi
import com.shreyas.go_train_schedule.models.MetrolinxResponse
import com.shreyas.go_train_schedule.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

interface MetrolinxRepository {

    suspend fun getAllGoTrainsServiceInfo(): Flow<ResultWrapper<MetrolinxResponse>>

    suspend fun getAllGoTrainDeparturesFromUnion(): Flow<ResultWrapper<MetrolinxResponse>>

    suspend fun getAllGoTrainStops(): Flow<ResultWrapper<MetrolinxResponse>>

    suspend fun getFareFromStopCodeToStopCode(
        fromStopCode: String,
        toStopCode: String
    ): Flow<ResultWrapper<MetrolinxResponse>>
}

@Singleton
class MetrolinxRepositoryImpl @Inject constructor(
    private val service: GoApi,
) : MetrolinxRepository {

    override suspend fun getAllGoTrainsServiceInfo(): Flow<ResultWrapper<MetrolinxResponse>> =
        flow {
            emit(ResultWrapper.LOADING(isLoading = true))

            val response = service.getAllGoTrainServicesAtGlance()
            try {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        Log.d(TAG, "Fetched Response for All Go Trains Schedule: ")
                        emit(ResultWrapper.LOADING(isLoading = false))
                        emit(ResultWrapper.SUCCESS(value = it))
                    }
                } else {
                    emit(ResultWrapper.LOADING(isLoading = false))
                    emit(ResultWrapper.FAILURE(code = null))
                }
            } catch (e: Exception) {
                emit(ResultWrapper.LOADING(isLoading = false))
                emit(ResultWrapper.FAILURE(code = e.message))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getAllGoTrainDeparturesFromUnion(): Flow<ResultWrapper<MetrolinxResponse>> =
        flow {
            emit(ResultWrapper.LOADING(isLoading = true))

            val response = service.getAllGoTrainUnionDepartures()
            try {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        Log.d(TAG, "Fetched Response for All Go Trains Departure from Union: ")
                        emit(ResultWrapper.LOADING(isLoading = false))
                        emit(ResultWrapper.SUCCESS(value = it))
                    }
                } else {
                    emit(ResultWrapper.LOADING(isLoading = false))
                    emit(ResultWrapper.FAILURE(code = null))
                }
            } catch (e: Exception) {
                emit(ResultWrapper.LOADING(isLoading = false))
                emit(ResultWrapper.FAILURE(code = e.message))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getAllGoTrainStops(): Flow<ResultWrapper<MetrolinxResponse>> = flow {
        emit(ResultWrapper.LOADING(isLoading = true))

        val response = service.getAllTrainStops()
        try {
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let {
                    Log.d(TAG, "Fetched Response for All Stop Name/Codes for GO: ")
                    emit(ResultWrapper.LOADING(isLoading = false))
                    emit(ResultWrapper.SUCCESS(value = it))
                }
            } else {
                emit(ResultWrapper.LOADING(isLoading = false))
                emit(ResultWrapper.FAILURE(code = null))
            }
        } catch (e: Exception) {
            emit(ResultWrapper.LOADING(isLoading = false))
            emit(ResultWrapper.FAILURE(code = e.message))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getFareFromStopCodeToStopCode(
        fromStopCode: String,
        toStopCode: String
    ): Flow<ResultWrapper<MetrolinxResponse>> = flow<ResultWrapper<MetrolinxResponse>> {
        emit(ResultWrapper.LOADING(isLoading = true))

        val response =
            service.getFaresFromAndTo(fromStopCode = fromStopCode, toStopCode = toStopCode)

        try {
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let {
                    Log.d(TAG, "Fetched Response for Fares for fromStop toStop: ")
                    emit(ResultWrapper.LOADING(isLoading = false))
                    emit(ResultWrapper.SUCCESS(value = it))
                }
            }
        } catch (e: Exception) {
            emit(ResultWrapper.LOADING(isLoading = false))
            emit(ResultWrapper.FAILURE(code = e.message))
        }
    }.flowOn(Dispatchers.IO)

    private companion object {
        val TAG = MetrolinxRepositoryImpl::class.simpleName
    }
}