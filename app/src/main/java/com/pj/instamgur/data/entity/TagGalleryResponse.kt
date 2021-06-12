package com.pj.instamgur.data.entity

import com.google.gson.annotations.SerializedName

class TagGalleryResponse(
    @SerializedName("data") val data: TagResponse?,
    @SerializedName("status") val status: Int?,
    @SerializedName("success") val success: Boolean?
)