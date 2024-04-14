package com.sumitkumarpandit.pixflow.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumitkumarpandit.pixflow.data.UPictures
import com.sumitkumarpandit.pixflow.repository.PixFlowRepository
import kotlinx.coroutines.launch

class PixFlowViewModel(private val repository: PixFlowRepository) : ViewModel() {

    private val _pictures = MutableLiveData<List<UPictures>>()
    val pictures: LiveData<List<UPictures>> = _pictures

    fun getPictureUrls(apiKey: String, page: Int, perPage: Int) {
        viewModelScope.launch {
            try {
                _pictures.value = repository.getPictureUrlsApi(apiKey,page,perPage)
            } catch (e: Exception) {
//                Log.e("", "getPictures: ", )
            }
        }
    }
}