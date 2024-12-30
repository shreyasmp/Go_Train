package com.shreyas.go_train_schedule.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Station(
    @SerializedName("LocationCode")
    val locationCode: String,
    @SerializedName("PublicStopId")
    val publicStopId: String,
    @SerializedName("LocationName")
    val locationName: String,
    @SerializedName("LocationType")
    val locationType: String,
) : Parcelable
