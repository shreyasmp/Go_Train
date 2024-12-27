package com.shreyas.go_train_schedule.ui.tabs

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import com.shreyas.go_train_schedule.R
import com.shreyas.go_train_schedule.ui.FarePricingList
import com.shreyas.go_train_schedule.viewmodel.MetrolinxViewModel

@Composable
fun FarePriceScreen(
    viewModel: MetrolinxViewModel
) {
    // Implement your Settings screen UI here
    val metroLinxResponse by viewModel.metroLinxResponse.observeAsState()
    val isLoading by viewModel.isLoading
    val isError by viewModel.isError

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text(text = stringResource(R.string.title_go_fare))
                    }
                }
            )
        },
        content = { paddingValues ->
            FarePricingList(
                paddingValues = paddingValues
            )
        }
    )
}