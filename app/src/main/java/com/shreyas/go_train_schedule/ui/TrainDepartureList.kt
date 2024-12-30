package com.shreyas.go_train_schedule.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shreyas.go_train_schedule.models.DepartureTrip
import com.shreyas.go_train_schedule.ui.theme.MetrolinxTheme
import com.shreyas.go_train_schedule.utils.DataProvider

@Composable
fun TrainDeparturesList(
    paddingValues: PaddingValues,
    departureTrips: MutableList<DepartureTrip>?,
) {
    var expandedItemIndex by remember { mutableIntStateOf(-1) }

    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        departureTrips?.let { departureTrip ->
            departureTrip.sortBy { trips ->
                trips.info
            }.run {
                itemsIndexed(departureTrips) { index, departTrip ->
                    DepartureTripCard(
                        departureTrip = departTrip,
                        isExpanded = expandedItemIndex == index,
                        onClick = {
                            expandedItemIndex = if (expandedItemIndex == index) -1 else index
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Preview("Night", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun ShowTrainDepartureList() {
    MetrolinxTheme {
        TrainDeparturesList(
            paddingValues = PaddingValues(4.dp),
            departureTrips = DataProvider.departureTrips,
        )
    }
}
