package com.shreyas.go_train_schedule.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllFares(
    @SerializedName("FareCategory")
    val fareCategory: List<FareCategory>
) : Parcelable

@Parcelize
data class FareCategory(
    @SerializedName("Type")
    val type: String,
    @SerializedName("Tickets")
    val tickets: List<Ticket>
) : Parcelable

@Parcelize
data class Ticket(
    @SerializedName("Type")
    val type: String,
    @SerializedName("Fares")
    val fares: List<Fare>
) : Parcelable

@Parcelize
data class Fare(
    @SerializedName("Type")
    val type: String,
    @SerializedName("Amount")
    val amount: Double,
    @SerializedName("Category")
    val category: String
) : Parcelable