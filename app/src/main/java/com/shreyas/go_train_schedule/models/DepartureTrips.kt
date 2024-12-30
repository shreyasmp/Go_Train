package com.shreyas.go_train_schedule.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DepartureTrips(
    @SerializedName("Trip")
    val departureTrips: List<DepartureTrip>,
) : Parcelable

@Parcelize
data class DepartureTrip(
    @SerializedName("Info")
    val info: String,
    @SerializedName("TripNumber")
    val tripNumber: String,
    @SerializedName("Platform")
    val platform: String,
    @SerializedName("Service")
    val service: String,
    @SerializedName("ServiceType")
    val serviceType: String,
    @SerializedName("Time")
    val time: String,
    @SerializedName("Stops")
    val stops: List<Stop>,
) : Parcelable

@Parcelize
data class Stop(
    @SerializedName("Name")
    val name: String,
    @SerializedName("Code")
    val code: String? = null,
) : Parcelable
