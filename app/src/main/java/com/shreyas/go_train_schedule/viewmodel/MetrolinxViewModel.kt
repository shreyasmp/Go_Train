package com.shreyas.go_train_schedule.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shreyas.go_train_schedule.models.MetrolinxResponse
import com.shreyas.go_train_schedule.models.Station
import com.shreyas.go_train_schedule.repository.MetrolinxRepository
import com.shreyas.go_train_schedule.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MetrolinxViewModel @Inject constructor(
    private val repository: MetrolinxRepository
) : ViewModel() {

    private val _metroLinxResponse: MutableLiveData<MetrolinxResponse?> = MutableLiveData()
    val metroLinxResponse: LiveData<MetrolinxResponse?> = _metroLinxResponse

    private val _metroLinxErrorResponse: MutableLiveData<String?> = MutableLiveData()
    val metroLinxErrorResponse: LiveData<String?> = _metroLinxErrorResponse

    val stationList: MutableList<Station> = mutableListOf()

    val isError: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: MutableState<Boolean> = mutableStateOf(false)

    // Call the fetch Stops only once on app launch, store it in stationsList
    init {
        fetchAllGoTrainStops()
    }

    internal fun fetchAllGoTrainsInfo() {
        viewModelScope.launch {
            repository.getAllGoTrainsServiceInfo().collect { result ->
                when (result) {
                    is ResultWrapper.LOADING -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = true
                            isError.value = false
                        }
                    }

                    is ResultWrapper.SUCCESS -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = false
                            result.value?.trips?.let {
                                isError.value = false
                                _metroLinxResponse.value = result.value
                            } ?: run {
                                isError.value = true
                                _metroLinxErrorResponse.value = NO_TRAIN_INFO_MESSAGE
                            }
                        }
                    }

                    is ResultWrapper.FAILURE -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = false
                            isError.value = true
                            _metroLinxErrorResponse.value = result.code
                        }
                    }
                }
            }
        }
    }

    internal fun fetchAllGoTrainDeparturesFromUnion() {
        viewModelScope.launch {
            repository.getAllGoTrainDeparturesFromUnion().collect { result ->
                when (result) {
                    is ResultWrapper.LOADING -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = true
                            isError.value = false
                        }
                    }

                    is ResultWrapper.SUCCESS -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = false
                            result.value?.allDepartures?.let {
                                isError.value = false
                                _metroLinxResponse.value = result.value
                            } ?: run {
                                isError.value = true
                                _metroLinxErrorResponse.value = NO_DEPARTURE_INFO_MESSAGE
                            }
                        }
                    }

                    is ResultWrapper.FAILURE -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = false
                            isError.value = true
                            _metroLinxErrorResponse.value = result.code
                        }
                    }
                }
            }
        }
    }

    internal fun fetchAllGoTrainStops() {
        viewModelScope.launch {
            repository.getAllGoTrainStops().collect { result ->
                when (result) {
                    is ResultWrapper.LOADING -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = true
                            isError.value = false
                        }
                    }

                    is ResultWrapper.SUCCESS -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = false
                            isError.value = false
                            result.value?.stations?.let { stations ->
                                stationList.addAll(stations.station)
                            }
                        }
                    }

                    is ResultWrapper.FAILURE -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = false
                            isError.value = true
                            _metroLinxErrorResponse.value = result.code
                        }
                    }
                }
            }
        }
    }

    internal fun fetchAllFaresFromStopToStop(fromStop: String, toStop: String) {
        viewModelScope.launch {
            repository.getFareFromStopCodeToStopCode(fromStopCode = fromStop, toStopCode = toStop)
                .collect { result ->
                    when (result) {
                        is ResultWrapper.LOADING -> {
                            withContext(Dispatchers.Main) {
                                isLoading.value = true
                                isError.value = false
                            }
                        }

                        is ResultWrapper.SUCCESS -> {
                            withContext(Dispatchers.Main) {
                                isLoading.value = false
                                result.value?.allFares?.let {
                                    isError.value = false
                                    _metroLinxResponse.value = result.value
                                } ?: run {
                                    isError.value = true
                                    _metroLinxErrorResponse.value = NO_FARE_INFORMATION_MESSAGE
                                }
                            }
                        }

                        is ResultWrapper.FAILURE -> {
                            withContext(Dispatchers.Main) {
                                isLoading.value = false
                                isError.value = true
                                _metroLinxErrorResponse.value = result.code
                            }
                        }
                    }
                }
        }
    }

    internal fun fetchStooDetailsFromStopCode(stopCode: String) {
        viewModelScope.launch {
            repository.getStopDetailsFromStopCode(stopCode = stopCode).collect { result ->
                when (result) {
                    is ResultWrapper.LOADING -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = true
                            isError.value = false
                        }
                    }

                    is ResultWrapper.SUCCESS -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = false
                            result.value?.stop?.let {
                                isError.value = false
                                _metroLinxResponse.value = result.value
                            }
                        }
                    }

                    is ResultWrapper.FAILURE -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = false
                            isError.value = true
                            _metroLinxErrorResponse.value = result.code
                        }
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        _metroLinxResponse.value = null
        _metroLinxErrorResponse.value = null
        isLoading.value = false
        isError.value = false
    }

    private companion object {
        const val NO_TRAIN_INFO_MESSAGE =
            "No Scheduled Trains, right now. Pull to Refresh or check back later"
        const val NO_DEPARTURE_INFO_MESSAGE =
            "No Union Departure Info Available, right now. Pull to Refresh or check back later"
        const val NO_FARE_INFORMATION_MESSAGE =
            "No Fare Information available, please check back later"
    }
}