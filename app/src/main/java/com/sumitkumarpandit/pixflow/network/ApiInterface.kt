package com.sumitkumarpandit.pixflow.network

import com.sumitkumarpandit.pixflow.data.UPictures
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("photos")
    suspend fun getPicturesApiData(
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<UPictures>>

}