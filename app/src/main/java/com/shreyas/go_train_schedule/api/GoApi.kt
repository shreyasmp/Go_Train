package com.shreyas.go_train_schedule.api

import com.shreyas.go_train_schedule.models.MetrolinxResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GoApi {

    @GET("ServiceataGlance/Trains/All")
    suspend fun getAllGoTrainServicesAtGlance(): Response<MetrolinxResponse>

    @GET("ServiceUpdate/UnionDepartures/All")
    suspend fun getAllGoTrainUnionDepartures(): Response<MetrolinxResponse>

    @GET("Stop/All")
    suspend fun getAllTrainStops(): Response<MetrolinxResponse>

    @GET("Fares/{FromStopCode}/{ToStopCode}")
    suspend fun getFaresFromAndTo(
        @Path("FromStopCode") fromStopCode: String,
        @Path("ToStopCode") toStopCode: String,
    ): Response<MetrolinxResponse>

    @GET("Stop/Details/{StopCode}")
    suspend fun getStopDetails(
        @Path("StopCode") stopCode: String
    ): Response<MetrolinxResponse>

    @GET("Schedule/Journey/{Date}/{FromStopCode}/{ToStopCode}/{StartTime}/{MaxJourney}")
    suspend fun getEveryStopTimePerRouteForGivenDate(
        @Path("Date") date: String,
        @Path("FromStopCode") fromStopCode: String,
        @Path("ToStopCode") toStopCode: String,
        @Path("StartTime") startTime: String,
        @Path("MaxJourney") maxJourney: String = "3",
    ): Response<MetrolinxResponse>
}