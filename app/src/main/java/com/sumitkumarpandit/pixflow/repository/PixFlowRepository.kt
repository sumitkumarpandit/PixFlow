package com.sumitkumarpandit.pixflow.repository

import com.sumitkumarpandit.pixflow.data.UPictures
import com.sumitkumarpandit.pixflow.network.ApiInterface

class PixFlowRepository(private val apiInterface: ApiInterface) {
    suspend fun getPictureUrlsApi(apiKey: String, page: Int, perPage: Int): List<UPictures>? {
        return apiInterface.getPicturesApiData(apiKey, page, perPage)
    }

}
