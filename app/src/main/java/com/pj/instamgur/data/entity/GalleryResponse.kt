package com.pj.instamgur.data.entity

import com.google.gson.annotations.SerializedName

data class GalleryResponse(
    @SerializedName("description") val description: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("topPost") val topPost: ImageResponse
)