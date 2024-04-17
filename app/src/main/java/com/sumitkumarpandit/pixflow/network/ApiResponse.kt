package com.sumitkumarpandit.pixflow.network

import com.sumitkumarpandit.pixflow.data.UPictures
import okhttp3.ResponseBody

sealed class ApiResponse {
    data class Success(val pictures: List<UPictures>) : ApiResponse()
    data object Loading : ApiResponse()
    data class Error(val message: String) : ApiResponse()

}