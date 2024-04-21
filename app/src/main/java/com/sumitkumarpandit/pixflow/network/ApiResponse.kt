package com.sumitkumarpandit.pixflow.network

import com.sumitkumarpandit.pixflow.data.UPictures

sealed class ApiResponse {
    data class Success(val pictures: List<UPictures>) : ApiResponse()
    data object Loading : ApiResponse()
    data class Error(val code: Int, val message: String) : ApiResponse()

}