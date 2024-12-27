package com.shreyas.go_train_schedule.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shreyas.go_train_schedule.models.DepartureTrip
import com.shreyas.go_train_schedule.ui.theme.MetrolinxTheme
import com.shreyas.go_train_schedule.utils.DataProvider

@Composable
fun DepartureTripCard(
    departureTrip: DepartureTrip,
    isExpanded: Boolean,
    onClick: () -> Unit,
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
        Column {
            DepartureCardHeader(
                departureTrip = departureTrip,
                onClick = onClick
            )
            AnimatedVisibility(visible = isExpanded) {

            }
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(animationSpec = tween(300)) + fadeIn(
                    animationSpec = tween(300)
                ),
                exit = shrinkVertically(animationSpec = tween(300)) + fadeOut(
                    animationSpec = tween(300)
                )
            ) {
                Column {
                    departureTrip.stops.forEach { stop ->
                        DrawComposableText(
                            content = stop.name,
                            start = 16.dp,
                            top = 0.dp,
                            end = 12.dp,
                            bottom = 8.dp,
                            weightOfFont = FontWeight.Normal,
                            textSize = 10.sp,
                            styleOfText = MaterialTheme.typography.h6,
                            maxLine = 1,
                            tag = "stopName"
                        )
                    }
                }
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun HorizontalDivider() {
    Divider(
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
        thickness = 1.dp,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Preview("Night", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun ShowDepartureTripCard() {
    MetrolinxTheme {
        DepartureTripCard(
            departureTrip = DataProvider.departureTrip,
            isExpanded = false,
            onClick = { }
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