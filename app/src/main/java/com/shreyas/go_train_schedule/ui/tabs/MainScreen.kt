package com.shreyas.go_train_schedule.ui.tabs

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shreyas.go_train_schedule.ui.BottomNavigationBar
import com.shreyas.go_train_schedule.ui.ShowErrorMessage
import com.shreyas.go_train_schedule.viewmodel.MetrolinxViewModel

@Composable
fun MainScreen(viewModel: MetrolinxViewModel) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val isConnected by viewModel.isConnected.observeAsState()
    val errorMessage by viewModel.metroLinxErrorResponse.observeAsState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "arrivals",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("arrivals") { ArrivalsScreen(viewModel, navController) }
            composable("departures") { DeparturesScreen(viewModel) }
            composable("pricing") { FarePriceScreen(viewModel) }
            composable("info") { InfoScreen() }
        }

        // Do not display network error message on Info Tab
        if (isConnected == false && currentRoute != "info") {
            errorMessage?.let {
                ShowErrorMessage(errorMessage = it)
            }
        }
    }
}