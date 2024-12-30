package com.shreyas.go_train_schedule.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shreyas.go_train_schedule.models.Station
import com.shreyas.go_train_schedule.models.Trip
import com.shreyas.go_train_schedule.ui.theme.MetrolinxTheme
import com.shreyas.go_train_schedule.utils.DataProvider

private const val EMPTY_LIST_NULL_LINES =
    "No Trains at this time, that are running to or from Union"

@Composable
fun LineList(
    paddingValues: PaddingValues,
    stationList: MutableList<Station>,
    lineList: MutableList<Trip>?,
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        lineList?.sortedBy { it.endTime }?.let { sortedTrips ->
            var isErrorShown = false
            items(sortedTrips) { trip ->
                trip.display?.let {
                    LineCard(
                        trip = trip,
                        stations = stationList,
                    )
                } ?: if (!isErrorShown) {
                    isErrorShown = true
                    ShowErrorMessage(errorMessage = EMPTY_LIST_NULL_LINES)
                } else {
                    // Nothing to do
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Preview("Night", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun ShowLineList() {
    MetrolinxTheme {
        LineList(
            paddingValues = PaddingValues(4.dp),
            stationList = DataProvider.stations,
            lineList = DataProvider.trips,
        )
    }
}