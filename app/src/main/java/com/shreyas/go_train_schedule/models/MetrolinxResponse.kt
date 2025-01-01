package com.shreyas.go_train_schedule.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MetrolinxResponse(
    @SerializedName("Metadata")
    val metadata: Metadata,
    @SerializedName("Trips")
    val trips: Trips?,
    @SerializedName("AllDepartures")
    val allDepartures: DepartureTrips?,
    @SerializedName("Stations")
    val stations: Stations?,
    @SerializedName("AllFares")
    val allFares: AllFares?,
    @SerializedName("Stop")
    val stop: GoStop?,
) : Parcelable

@Parcelize
data class Metadata(
    @SerializedName("TimeStamp")
    val timeStamp: String?,
    @SerializedName("ErrorCode")
    val errorCode: String?,
    @SerializedName("ErrorMessage")
    val errorMessage: String?,
) : Parcelable
