package com.sumitkumarpandit.pixflow.network

import com.sumitkumarpandit.pixflow.data.UPictures
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("photos")
    suspend fun getPictures(
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<UPictures>

}