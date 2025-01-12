package com.shreyas.go_train_schedule.utils

sealed class ResultWrapper<out T : Any> {
    data class LOADING(
        val isLoading: Boolean
    ) : ResultWrapper<Nothing>()
    data class SUCCESS<out T : Any>(val value: T?) : ResultWrapper<T>()
    data class FAILURE(
        val code: String
    ) : ResultWrapper<Nothing>()

    val success: Boolean
        get() = this is SUCCESS
}