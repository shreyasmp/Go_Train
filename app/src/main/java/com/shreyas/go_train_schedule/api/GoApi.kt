package com.shreyas.go_train_schedule.api

import com.shreyas.go_train_schedule.models.MetrolinxResponse
import retrofit2.Response
import retrofit2.http.GET

interface GoApi {

    @GET("ServiceataGlance/Trains/All")
    suspend fun getAllGoTrainServicesAtGlance(): Response<MetrolinxResponse>

    @GET("ServiceUpdate/UnionDepartures/All")
    suspend fun getAllGoTrainUnionDepartures(): Response<MetrolinxResponse>

    @GET("Stop/All")
    suspend fun getAllTrainStops(): Response<MetrolinxResponse>
}