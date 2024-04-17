package com.sumitkumarpandit.pixflow.network

import okhttp3.ResponseBody

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data object Loading : ApiResponse<Nothing>()
    data class Failure<out T>(
        val isNetworkError:Boolean,
        val errorCode: Int,
        val errorBody: ResponseBody?
    ) : ApiResponse<T>()

}
