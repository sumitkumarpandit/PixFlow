package com.sumitkumarpandit.pixflow.network

import com.sumitkumarpandit.pixflow.data.UPictures
import retrofit2.http.GET

interface ApiInterface {
    @GET("photos")
    suspend fun getPhotos(): List<UPictures>

}