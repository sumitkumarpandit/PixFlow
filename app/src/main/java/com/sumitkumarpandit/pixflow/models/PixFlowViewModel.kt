package com.sumitkumarpandit.pixflow.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumitkumarpandit.pixflow.data.UPictures
import com.sumitkumarpandit.pixflow.network.ApiResponse
import com.sumitkumarpandit.pixflow.repository.PixFlowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PixFlowViewModel @Inject constructor(private val repository: PixFlowRepository) :
    ViewModel() {

//    val pictures: StateFlow<List<UPictures>>
//        get() = repository.pictures

    val pictures: StateFlow<ApiResponse> = repository.pictures
    fun getPictureUrls(apiKey: String) =
        viewModelScope.launch {
            repository.getPictureUrlsApi(apiKey)
        }
}