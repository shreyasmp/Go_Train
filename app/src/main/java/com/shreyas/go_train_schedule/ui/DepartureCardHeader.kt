package com.shreyas.go_train_schedule.ui

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shreyas.go_train_schedule.helper.MetrolinxUtility
import com.shreyas.go_train_schedule.models.DepartureTrip
import com.shreyas.go_train_schedule.ui.theme.MetrolinxTheme
import com.shreyas.go_train_schedule.utils.DataProvider

@Composable
fun DepartureCardHeader(
    modifier: Modifier = Modifier,
    departureTrip: DepartureTrip,
    onClick: () -> Unit = {},
) {

    val lastStopName = departureTrip.stops.lastOrNull()?.name ?: ""

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() },
    ) {
        Column(
            modifier = modifier
                .weight(2f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            DrawComposableText(
                content = "${departureTrip.service} ($lastStopName)",
                start = 0.dp,
                top = 0.dp,
                end = 0.dp,
                bottom = 0.dp,
                weightOfFont = FontWeight.Bold,
                textSize = 14.sp,
                styleOfText = MaterialTheme.typography.h6,
                maxLine = 1,
                tag = "serviceName"
            )

            DrawComposableText(
                content = MetrolinxUtility.getTimeFormatFromString(departureTrip.time),
                start = 0.dp,
                top = 0.dp,
                end = 12.dp,
                bottom = 0.dp,
                weightOfFont = FontWeight.Normal,
                textSize = 10.sp,
                styleOfText = MaterialTheme.typography.h6,
                maxLine = 1,
                tag = "trainTime"
            )

            DrawComposableText(
                content = "Stops (tap to expand)",
                start = 0.dp,
                top = 0.dp,
                end = 12.dp,
                bottom = 0.dp,
                weightOfFont = FontWeight.Normal,
                textSize = 10.sp,
                styleOfText = MaterialTheme.typography.h6,
                maxLine = 1,
                tag = "stopsLabel"
            )
        }

        Column(
            modifier = modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            DrawComposableText(
                content = "Platform: " + departureTrip.platform,
                start = 4.dp,
                top = 0.dp,
                end = 0.dp,
                bottom = 0.dp,
                weightOfFont = FontWeight.Bold,
                textSize = 14.sp,
                styleOfText = MaterialTheme.typography.h6,
                maxLine = 1,
                tag = "platform"
            )

            DrawComposableText(
                content = departureTrip.info.substringBefore(" / "),
                start = 4.dp,
                top = 0.dp,
                end = 0.dp,
                bottom = 0.dp,
                weightOfFont = FontWeight.Bold,
                textSize = 11.sp,
                styleOfText = MaterialTheme.typography.h6,
                maxLine = 1,
                colour = if (departureTrip.info.substringBefore(" / ") == "Proceed"
                ) Color.Green else Color(0xFFFFA500),
                tag = "infoName"
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Preview("Night", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun ShowDepartureCardHeader() {
    MetrolinxTheme {
        DepartureCardHeader(
            departureTrip = DataProvider.departureTrip
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
    colour: Color = MaterialTheme.colors.surface,
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
            color = colour,
            fontWeight = weightOfFont,
            fontSize = textSize,
            style = styleOfText,
            maxLines = maxLine
        )
    }
}