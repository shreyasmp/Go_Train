package com.shreyas.go_train_schedule.ui

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.shreyas.go_train_schedule.helper.MetrolinxUtility
import com.shreyas.go_train_schedule.models.Station
import com.shreyas.go_train_schedule.models.Trip
import com.shreyas.go_train_schedule.ui.theme.MetrolinxTheme
import com.shreyas.go_train_schedule.utils.DataProvider

@Composable
fun LineCard(
    trip: Trip,
    stations: MutableList<Station>,
) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.onSurface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp, 0.dp, 16.dp)
                .clickable {

                }
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(MetrolinxUtility.resolveLineIcon(trip.lineCode))
                    .size(Size.ORIGINAL)
                    .build(),
                contentDescription = "Go Train Line Icon",
                modifier = Modifier.size(76.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                DrawComposableText(
                    content =
                    MetrolinxUtility.resolveRouteName(
                        trip.lineCode
                    ) + " " + "(" + trip.display + ")",
                    start = 0.dp,
                    top = 0.dp,
                    end = 0.dp,
                    bottom = 0.dp,
                    weightOfFont = FontWeight.Bold,
                    textSize = 14.sp,
                    styleOfText = MaterialTheme.typography.h6,
                    maxLine = 2,
                    tag = "trainDisplayName"
                )

                Spacer(modifier = Modifier.height(4.dp))

                DrawComposableText(
                    content =
                    MetrolinxUtility.convertTo12HourFormat(time24 = trip.startTime) +
                            " -> " +
                            MetrolinxUtility.convertTo12HourFormat(time24 = trip.endTime) +
                            " (" + MetrolinxUtility.getTimeDifferenceInHourOrMinute(
                        startTime = trip.startTime, endTime = trip.endTime
                    ) + ")",
                    start = 0.dp,
                    top = 0.dp,
                    end = 0.dp,
                    bottom = 0.dp,
                    weightOfFont = FontWeight.Normal,
                    textSize = 11.sp,
                    styleOfText = MaterialTheme.typography.h6,
                    maxLine = 1,
                    tag = "tripDuration"
                )

                Spacer(modifier = Modifier.height(2.dp))

                DrawComposableText(
                    content = "From: " + MetrolinxUtility.getGOStationNameFromGoFirstStop(
                        trip.firstStopCode,
                        stationList = stations
                    ),
                    start = 0.dp,
                    top = 0.dp,
                    end = 0.dp,
                    bottom = 0.dp,
                    weightOfFont = FontWeight.Normal,
                    textSize = 11.sp,
                    styleOfText = MaterialTheme.typography.h6,
                    maxLine = 1,
                    tag = "startingStation"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Preview("Night", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun ShowLineCard() {
    MetrolinxTheme {
        LineCard(
            trip = DataProvider.trip,
            stations = DataProvider.stations,
        )
    }
}

@Composable
private fun DrawComposableText(
    content: String?,
    start: Dp,
    top: Dp,
    end: Dp,
    bottom: Dp,
    weightOfFont: FontWeight?,
    textSize: TextUnit,
    styleOfText: TextStyle,
    maxLine: Int,
    tag: String
) {
    if (content != null) {
        Text(
            text = content,
            modifier = Modifier
                .padding(start, top, end, bottom)
                .testTag(tag),
            color = MaterialTheme.colors.surface,
            fontWeight = weightOfFont,
            fontSize = textSize,
            style = styleOfText,
            maxLines = maxLine
        )
    }
}
