package com.shreyas.go_train_schedule.helper

import com.shreyas.go_train_schedule.R
import com.shreyas.go_train_schedule.models.Station
import org.junit.Assert.assertEquals
import org.junit.Test

class MetrolinxUtilityTest {

    @Test
    fun testResolveRouteName() {
        assertEquals("Barrie", MetrolinxUtility.resolveRouteName("BR"))
        assertEquals("Unknown Line Code", MetrolinxUtility.resolveRouteName("XX"))
    }

    @Test
    fun testGetGOStationNameFromGoFirstStop() {
        val stationList = listOf(
            Station(
                locationCode = "UN",
                locationName = "Union Station",
                publicStopId = "1UN",
                locationType = "Train Station"
            ),
            Station(
                locationCode = "WR",
                locationName = "West Harbour GO",
                publicStopId = "1WR",
                locationType = "Train Station"
            ),
        )
        assertEquals(
            "Union Station",
            MetrolinxUtility.getGOStationNameFromGoFirstStop("UN", stationList)
        )
        assertEquals(
            "Unknown Stop Code XX",
            MetrolinxUtility.getGOStationNameFromGoFirstStop("XX", stationList)
        )
    }

    @Test
    fun testConvertTo12HourFormat() {
        val time24 = "14:30"
        val expectedTime12 = "02:30 PM"
        assertEquals(expectedTime12, MetrolinxUtility.convertTo12HourFormat(time24))
    }

    @Test
    fun testGetTimeDifferenceInHourOrMinute() {
        val startTime = "14:30"
        val endTime = "16:00"
        val expectedDifference = "1 hour and 30 minutes"
        assertEquals(
            expectedDifference,
            MetrolinxUtility.getTimeDifferenceInHourOrMinute(startTime, endTime)
        )
    }

    @Test
    fun testGetTimeFormatFromString() {
        val date = "2023-10-10 14:30:00"
        val expectedFormattedDate = "10/10/2023 02:30 PM"
        assertEquals(expectedFormattedDate, MetrolinxUtility.getTimeFormatFromString(date))
    }

    @Test
    fun testResolveLineIcon() {
        assertEquals(R.drawable.br_line, MetrolinxUtility.resolveLineIcon("BR"))
        assertEquals(R.drawable.ic_home_black_24dp, MetrolinxUtility.resolveLineIcon("XX"))
    }
}