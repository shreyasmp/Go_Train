package com.shreyas.go_train_schedule.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stop(
    @SerializedName("Name")
    val name: String,
    @SerializedName("Code")
    val code: String? = null,
) : Parcelable
