package com.shreyas.go_train_schedule.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Metadata(
    @SerializedName("TimeStamp")
    val timeStamp: String?,
    @SerializedName("ErrorCode")
    val errorCode: String?,
    @SerializedName("ErrorMessage")
    val errorMessage: String?,
) : Parcelable
