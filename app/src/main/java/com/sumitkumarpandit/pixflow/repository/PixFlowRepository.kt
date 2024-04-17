package com.sumitkumarpandit.pixflow.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sumitkumarpandit.pixflow.data.UPictures
import com.sumitkumarpandit.pixflow.network.ApiInterface
import com.sumitkumarpandit.pixflow.network.ApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class PixFlowRepository @Inject constructor(private val apiInterface: ApiInterface) {

    private val _picturesResponse = MutableStateFlow<ApiResponse>(ApiResponse.Loading)
    val picturesResponse: StateFlow<ApiResponse>
        get() = _picturesResponse

    private val _pictures = MutableStateFlow<List<UPictures>>(emptyList())
    val pictures: StateFlow<List<UPictures>>
        get() = _pictures

    private val perPage = 30
    private var page = 1
    suspend fun getPictureUrlsApi(apiKey: String) {
        try {
            val response = apiInterface.getPicturesApiData(apiKey, page, perPage)
            if (response.isSuccessful && response.body() != null) {
                val newPics = _pictures.value.toMutableList()
                newPics.addAll(response.body()!!)
                _pictures.emit(newPics)
                _picturesResponse.value = ApiResponse.Success(newPics)
                page++;
            } else {
                _picturesResponse.value =
                    ApiResponse.Error("Failed to fetch pictures: ${response.message()}")
            }
        } catch (e: Exception) {
            _picturesResponse.value = ApiResponse.Error("Failed to fetch pictures: ${e.message}")
        }
    }
}

