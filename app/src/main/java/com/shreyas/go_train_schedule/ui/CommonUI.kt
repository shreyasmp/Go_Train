package com.shreyas.go_train_schedule.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shreyas.go_train_schedule.ui.theme.MetrolinxTheme

@Composable
fun ShowErrorMessage(
    errorMessage: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = errorMessage, fontSize = 16.sp)
    }
}

@Composable
fun ShowCircularProgressIndicator() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Preview("Night", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun ShowErrorMessagePreview() {
    MetrolinxTheme {
        ShowErrorMessage(
            errorMessage = "No Scheduled Trains, right now. Pull to Refresh or check back later"
        )
    }
}