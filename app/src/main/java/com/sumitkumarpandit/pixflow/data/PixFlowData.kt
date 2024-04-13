package com.sumitkumarpandit.pixflow.data

import com.google.gson.annotations.SerializedName

data class UPictures(
    @SerializedName("urls") val  urls: URLs
)

data class URLs (
    @SerializedName("regular") val regular: String
)
