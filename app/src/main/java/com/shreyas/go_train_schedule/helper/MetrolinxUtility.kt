package com.shreyas.go_train_schedule.helper

import com.shreyas.go_train_schedule.R
import com.shreyas.go_train_schedule.models.RouteNames
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

object MetrolinxUtility {

    fun resolveRouteName(lineCode: String) = when (lineCode) {
        RouteNames.BARRIE_LINE.lineCode -> "Barrie"
        RouteNames.KITCHENER_LINE.lineCode -> "Kitchener"
        RouteNames.LAKESHORE_EAST_LINE.lineCode -> "Lakeshore East"
        RouteNames.LAKESHORE_WEST_LINE.lineCode -> "Lakeshore West"
        RouteNames.MILTON_LINE.lineCode -> "Milton"
        RouteNames.RICHMOND_HILL_LINE.lineCode -> "Richmond Hill"
        RouteNames.STOUFFVILLE_LINE.lineCode -> "Stouffville"
        RouteNames.UP_EXPRESS.lineCode -> "UP Express"
        RouteNames.GEORGETOWN_LINE.lineCode -> "Georgetown"
        else -> "Unknown Line Code"
    }

    fun resolveFirstStopCode(firstStop: String) =
        when (firstStop) {
            "UN" -> "Union Station"
            "WR" -> "West Harbour GO"
            "AL" -> "Aldershot GO"
            "OS" -> "Durham College Oshawa GO"
            "AU" -> "Aurora GO"
            "MJ" -> "Mount Joy GO"
            "MO" -> "Mount Pleasant GO"
            "AD" -> "AllanDale Waterfront GO"
            "NI" -> "Niagara Falls GO"
            "OA" -> "Oakville GO"
            "KI" -> "Kitchener GO"
            "BE" -> "Bramalea GO"
            "LI" -> "Old Elm GO"
            else -> "Unknown Stop Code $firstStop"
        }

    fun convertTo12HourFormat(time24: String): String? {
        val sdf24 = SimpleDateFormat("HH:mm", Locale.getDefault())
        val date = sdf24.parse(time24)

        val sdf12 = SimpleDateFormat("hh:mm a", Locale.getDefault())
        sdf12.timeZone = TimeZone.getDefault()
        return date?.let { sdf12.format(it) }
    }

    fun getTimeDifferenceInHourOrMinute(startTime: String, endTime: String): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val startDate = sdf.parse(startTime)
        val endDate = sdf.parse(endTime)

        // If end time is before start time, add 24 hours to end time
        if (endDate != null && endDate.before(startDate)) {
            val calendar = Calendar.getInstance()
            calendar.time = endDate
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            endDate.time = calendar.time.time
        }

        val differenceInMillis = startDate?.time?.let { endDate?.time?.minus(it) }
        val differenceInMinutes = differenceInMillis?.div((1000 * 60))
        val differenceInHours = differenceInMinutes?.div(60)
        val remainingMinutes = differenceInMinutes?.rem(60)

        return if (differenceInHours != null && differenceInHours > 0) {
            "$differenceInHours hour${if (differenceInHours > 1) "s" else ""} and $remainingMinutes minute${if (remainingMinutes != 1L) "s" else ""}"
        } else {
            "$remainingMinutes minute${if (remainingMinutes != 1L) "s" else ""}"
        }
    }

    fun getTimeFormatFromString(date: String): String {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a"))
    }

    fun resolveLineIcon(lineCode: String): Int {
        return when (lineCode) {
            RouteNames.BARRIE_LINE.lineCode -> R.drawable.br_line
            RouteNames.KITCHENER_LINE.lineCode -> R.drawable.ki_line
            RouteNames.LAKESHORE_EAST_LINE.lineCode -> R.drawable.le_line
            RouteNames.LAKESHORE_WEST_LINE.lineCode -> R.drawable.lw_line
            RouteNames.MILTON_LINE.lineCode -> R.drawable.mi_line
            RouteNames.RICHMOND_HILL_LINE.lineCode -> R.drawable.rh_line
            RouteNames.STOUFFVILLE_LINE.lineCode -> R.drawable.st_line
            RouteNames.UP_EXPRESS.lineCode -> R.drawable.up_line
            RouteNames.GEORGETOWN_LINE.lineCode -> R.drawable.ki_line
            else -> R.drawable.ic_home_black_24dp
        }
    }
}