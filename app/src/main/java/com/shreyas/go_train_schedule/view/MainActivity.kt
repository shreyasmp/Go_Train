package com.shreyas.go_train_schedule.view

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.shreyas.go_train_schedule.network.NetworkCallback
import com.shreyas.go_train_schedule.ui.tabs.MainScreen
import com.shreyas.go_train_schedule.ui.theme.MetrolinxTheme
import com.shreyas.go_train_schedule.viewmodel.MetrolinxViewModel
import dagger.android.AndroidInjection
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MetrolinxViewModel
    private lateinit var networkCallback: NetworkCallback
    private lateinit var connectivityManager: ConnectivityManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[MetrolinxViewModel::class.java]

        networkCallback = NetworkCallback(context = this)

        lifecycleScope.launch {
            networkCallback.isConnected.observe(this@MainActivity) { isConnected ->
                viewModel.updateNetworkStatus(isConnected = isConnected)
            }
        }

        setContent {
            MetrolinxTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen(viewModel = viewModel)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        // Create a network request to monitor internet connectivity
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        // Register the network callback to handle network changes
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    override fun onStop() {
        super.onStop()
        connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        //Unregister the network callback to handle network changes
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}
