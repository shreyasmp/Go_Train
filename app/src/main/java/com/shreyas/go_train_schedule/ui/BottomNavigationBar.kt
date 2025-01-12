package com.shreyas.go_train_schedule.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DepartureBoard
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PriceCheck
import androidx.compose.material.icons.filled.Train
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.shreyas.go_train_schedule.R

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Train, contentDescription = null) },
            label = { Text(stringResource(id = R.string.title_arrivals)) },
            selected = false,
            onClick = { navController.navigate("arrivals") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.DepartureBoard, contentDescription = null) },
            label = { Text(stringResource(id = R.string.title_union_departures)) },
            selected = false,
            onClick = { navController.navigate("departures") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.PriceCheck, contentDescription = null) },
            label = { Text(stringResource(id = R.string.title_price)) },
            selected = false,
            onClick = { navController.navigate("pricing") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Info, contentDescription = null) },
            label = { Text(stringResource(id = R.string.title_info)) },
            selected = false,
            onClick = { navController.navigate("info") }
        )
    }
}