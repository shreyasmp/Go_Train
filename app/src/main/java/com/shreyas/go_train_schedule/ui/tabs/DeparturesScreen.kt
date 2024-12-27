package com.shreyas.go_train_schedule.ui.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.shreyas.go_train_schedule.R
import com.shreyas.go_train_schedule.ui.ShowCircularProgressIndicator
import com.shreyas.go_train_schedule.ui.ShowErrorMessage
import com.shreyas.go_train_schedule.ui.TrainDeparturesList
import com.shreyas.go_train_schedule.viewmodel.MetrolinxViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DeparturesScreen(
    viewModel: MetrolinxViewModel
) {
    val metroLinxResponse by viewModel.metroLinxResponse.observeAsState()
    val isLoading by viewModel.isLoading
    val isError by viewModel.isError
    val metroLinxErrorResponse by viewModel.metroLinxErrorResponse.observeAsState()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isLoading,
        onRefresh = { viewModel.fetchAllGoTrainDeparturesFromUnion() }
    )

    // Trigger data fetch when the screen is displayed
    LaunchedEffect(Unit) {
        viewModel.fetchAllGoTrainDeparturesFromUnion()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text(text = stringResource(R.string.union_departures))
                    }
                }
            )
        },
        content = { padding ->
            if (isError) {
                metroLinxErrorResponse?.let { errorMessage ->
                    ShowErrorMessage(errorMessage = errorMessage)
                }
            } else {
                if (isLoading) {
                    ShowCircularProgressIndicator()
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .pullRefresh(pullRefreshState)
                    ) {
                        metroLinxResponse?.allDepartures?.let { departureTrips ->
                            TrainDeparturesList(
                                paddingValues = padding,
                                departureTrips = departureTrips.departureTrips.toMutableList()
                            )
                        }
                        PullRefreshIndicator(
                            refreshing = viewModel.isLoading.value,
                            state = pullRefreshState,
                            modifier = Modifier.align(Alignment.TopCenter),
                            backgroundColor = if (viewModel.isLoading.value) Color.Red else Color.Transparent
                        )
                    }
                }
            }
        }
    )
}
