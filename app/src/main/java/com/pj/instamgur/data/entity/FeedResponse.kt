package com.pj.instamgur.data.entity

import com.google.gson.annotations.SerializedName

data class FeedResponse(
    @SerializedName("data") val data: List<ImageResponse>?,
    @SerializedName("status") val status: Int?,
    @SerializedName("success") val success: Boolean?
)
