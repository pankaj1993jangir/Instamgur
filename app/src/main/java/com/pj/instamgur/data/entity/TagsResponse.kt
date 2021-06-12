package com.pj.instamgur.data.entity

import com.google.gson.annotations.SerializedName

data class TagsResponse(
    @SerializedName("data")
    val data: Data,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("success")
    val success: Boolean
)

data class Data(
    @SerializedName("featured")
    val featured: Any?,
    @SerializedName("galleries")
    val galleryResponses: List<GalleryResponse>,
    @SerializedName("tags")
    val tags: List<TagResponse>
)