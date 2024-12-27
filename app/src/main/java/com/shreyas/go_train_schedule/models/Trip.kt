package com.shreyas.go_train_schedule.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Trip(
    @SerializedName("Cars")
    val cars: String,
    @SerializedName("TripNumber")
    val tripNumber: String,
    @SerializedName("StartTime")
    val startTime: String,
    @SerializedName("EndTime")
    val endTime: String,
    @SerializedName("LineCode")
    val lineCode: String,
    @SerializedName("RouteNumber")
    val routeNumber: String,
    @SerializedName("VariantDir")
    val variantDir: String,
    @SerializedName("Display")
    val display: String?,
    @SerializedName("Latitude")
    val latitude: Double,
    @SerializedName("Longitude")
    val longitude: Double,
    @SerializedName("IsInMotion")
    val isInMotion: Boolean,
    @SerializedName("DelaySeconds")
    val delaySeconds: Int,
    @SerializedName("Course")
    val course: Double,
    @SerializedName("FirstStopCode")
    val firstStopCode: String,
    @SerializedName("LastStopCode")
    val lastStopCode: String,
    @SerializedName("PrevStopCode")
    val prevStopCode: String,
    @SerializedName("NextStopCode")
    val nextStopCode: String,
    @SerializedName("AtStationCode")
    val atStationCode: String?,
    @SerializedName("ModifiedDate")
    val modifiedDate: String,
    @SerializedName("OccupancyPercentage")
    val occupancyPercentage: Int?
) : Parcelable
