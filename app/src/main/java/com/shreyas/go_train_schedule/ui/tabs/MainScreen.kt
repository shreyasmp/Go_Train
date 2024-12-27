package com.shreyas.go_train_schedule.ui.tabs

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shreyas.go_train_schedule.view.BottomNavigationBar
import com.shreyas.go_train_schedule.viewmodel.MetrolinxViewModel

@Composable
fun MainScreen(viewModel: MetrolinxViewModel) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(viewModel) }
            composable("departures") { DeparturesScreen(viewModel) }
            composable("pricing") { FarePriceScreen(viewModel) }
            composable("info") { InfoScreen() }
        }
    }
}