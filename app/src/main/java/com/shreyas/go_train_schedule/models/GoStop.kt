package com.shreyas.go_train_schedule.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GoStop(
    @SerializedName("ZoneCode")
    val zoneCode: String,
    @SerializedName("StreetNumber")
    val streetNumber: String,
    @SerializedName("Intersection")
    val intersection: String,
    @SerializedName("City")
    val city: String,
    @SerializedName("StreetName")
    val streetName: String,
    @SerializedName("Code")
    val code: String,
    @SerializedName("StopName")
    val stopName: String,
    @SerializedName("StopNameFr")
    val stopNameFr: String,
    @SerializedName("IsBus")
    val isBus: Boolean,
    @SerializedName("IsTrain")
    val isTrain: Boolean,
    @SerializedName("Longitude")
    val longitude: String,
    @SerializedName("Latitude")
    val latitude: String,
    @SerializedName("DrivingDirections")
    val drivingDirections: String,
    @SerializedName("DrivingDirectionsFr")
    val drivingDirectionsFr: String,
    @SerializedName("BoardingInfo")
    val boardingInfo: String,
    @SerializedName("BoardingInfoFr")
    val boardingInfoFr: String,
    @SerializedName("TicketSales")
    val ticketSales: String,
    @SerializedName("TicketSalesFr")
    val ticketSalesFr: String,
    @SerializedName("Facilities")
    val facilities: List<Facility>,
    @SerializedName("Parkings")
    val parkings: List<Parking>,
    @SerializedName("Place")
    val place: Place
) : Parcelable


@Parcelize
data class Facility(
    @SerializedName("Code")
    val code: String,
    @SerializedName("Description")
    val description: String,
    @SerializedName("DescriptionFr")
    val descriptionFr: String
) : Parcelable

@Parcelize
data class Parking(
    @SerializedName("Name")
    val name: String,
    @SerializedName("NameFr")
    val nameFr: String,
    @SerializedName("ParkSpots")
    val parkSpots: Int,
    @SerializedName("Type")
    val type: String
) : Parcelable

@Parcelize
data class Place(
    @SerializedName("Code")
    val code: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Longitude")
    val longitude: String,
    @SerializedName("Latitude")
    val latitude: String,
    @SerializedName("Radius")
    val radius: String,
    @SerializedName("Stops")
    val stops: Stops
) : Parcelable

@Parcelize
data class Stops(
    @SerializedName("Stop")
    val stopList: List<StopDetail>
) : Parcelable

@Parcelize
data class StopDetail(
    @SerializedName("Code")
    val code: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("NameFr")
    val nameFr: String
) : Parcelable