package com.shreyas.go_train_schedule.utils

import com.shreyas.go_train_schedule.models.DepartureTrip
import com.shreyas.go_train_schedule.models.DepartureTrips
import com.shreyas.go_train_schedule.models.MetrolinxResponse
import com.shreyas.go_train_schedule.models.Station
import com.shreyas.go_train_schedule.models.Stations
import com.shreyas.go_train_schedule.models.Stop
import com.shreyas.go_train_schedule.models.Trip
import com.shreyas.go_train_schedule.models.Trips

object DataProvider {

    val trip = Trip(
        cars = "12",
        tripNumber = "1039",
        startTime = "23:14",
        endTime = "00:27",
        lineCode = "LW",
        routeNumber = "LW   ",
        variantDir = "W",
        display = "LW - Aldershot GO",
        latitude = 43.342124,
        longitude = -79.807611,
        isInMotion = false,
        delaySeconds = 2,
        course = 0.0,
        firstStopCode = "UN",
        lastStopCode = "AL",
        prevStopCode = "BU",
        nextStopCode = "OSBO",
        atStationCode = "BU",
        modifiedDate = "2024-12-26 00:19:03",
        occupancyPercentage = null,
    )

    val trip2 = Trip(
        cars = "6",
        tripNumber = "6937",
        startTime = "22:54",
        endTime = "00:37",
        lineCode = "BR",
        routeNumber = "BR   ",
        variantDir = "N",
        display = "BR - Allandale Waterfront GO",
        latitude = 44.276713,
        longitude = -79.55575,
        isInMotion = true,
        delaySeconds = -49,
        course = 2.0,
        firstStopCode = "UN",
        lastStopCode = "AD",
        prevStopCode = "LEFR",
        nextStopCode = "BA",
        atStationCode = null,
        modifiedDate = "2024-12-26 00:19:00",
        occupancyPercentage = null,
    )

    val trip3 = Trip(
        cars = "12",
        tripNumber = "1740",
        startTime = "23:23",
        endTime = "00:45",
        lineCode = "LW",
        routeNumber = "LW   ",
        variantDir = "E",
        display = "LW - Union Station",
        latitude = 43.591271,
        longitude = -79.546834,
        isInMotion = true,
        delaySeconds = 22,
        course = 53.0,
        firstStopCode = "WR",
        lastStopCode = "UN",
        prevStopCode = "REVU",
        nextStopCode = "LO",
        atStationCode = null,
        modifiedDate = "2024-12-26 00:19:10",
        occupancyPercentage = null
    )

    val trips = mutableListOf(trip, trip2, trip3)

    val departureTrip = DepartureTrip(
        info = "Proceed / Avancez",
        tripNumber = "1725",
        platform = "7 & 8",
        service = "Lakeshore West",
        serviceType = "T",
        time = "2024-12-26 16:44:00",
        stops = listOf(
            Stop(name = "Exhibition"),
            Stop(name = "Mimico"),
            Stop(name = "Long Brsnch"),
            Stop(name = "Port Credit"),
            Stop(name = "Clarkson"),
            Stop(name = "Oakville"),
            Stop(name = "Bronte"),
            Stop(name = "Appleby"),
            Stop(name = "Burlington"),
            Stop(name = "Aldershot"),
            Stop(name = "West Harbour"),
        )
    )

    val departureTrip2 = DepartureTrip(
        info = "Wait / Attendez",
        tripNumber = "7430",
        platform = "-",
        service = "Stouffville",
        serviceType = "T",
        time = "2024-12-26 19:11:00",
        stops = listOf(
            Stop(name = "Kennedy"),
            Stop(name = "Agincourt"),
            Stop(name = "Milliken"),
            Stop(name = "Unionville"),
            Stop(name = "Centennial"),
            Stop(name = "Markham"),
            Stop(name = "Mount Joy"),
        )
    )

    val departureTrips = mutableListOf(departureTrip, departureTrip2)

    val station1 = Station(
        locationCode = "OR",
        publicStopId = "1OR",
        locationName = "Oriole GO",
        locationType = "Train Station"
    )

    val station2 = Station(
        locationCode = "OS",
        publicStopId = "1OS",
        locationName = "Durham College Oshawa GO",
        locationType = "Train & Bus Station"
    )

    val stations = mutableListOf(station1, station2)

    val metroLinxResponse = MetrolinxResponse(
        metadata = com.shreyas.go_train_schedule.models.Metadata(
            timeStamp = "2024-12-26 16:34:33",
            errorCode = "200",
            errorMessage = "OK"
        ),
        trips = Trips(
            trip = listOf(trip, trip2)
        ),
        allDepartures = null,
        stations = null,
        allFares = null,
    )

    val metroLinxResponse2 = MetrolinxResponse(
        metadata = com.shreyas.go_train_schedule.models.Metadata(
            timeStamp = "2024-12-26 16:34:33",
            errorCode = "200",
            errorMessage = "OK"
        ),
        trips = null,
        allDepartures = DepartureTrips(
            departureTrips = listOf(departureTrip, departureTrip2)
        ),
        stations = null,
        allFares = null,
    )

    val metroLinxResponse3 = MetrolinxResponse(
        metadata = com.shreyas.go_train_schedule.models.Metadata(
            timeStamp = "2024-12-26 16:34:33",
            errorCode = "200",
            errorMessage = "OK"
        ),
        trips = null,
        allDepartures = null,
        stations = Stations(
            station = listOf(station1, station2)
        ),
        allFares = null,
    )
}