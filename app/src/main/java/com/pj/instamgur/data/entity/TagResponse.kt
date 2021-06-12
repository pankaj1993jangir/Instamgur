package com.pj.instamgur.data.entity

import com.google.gson.annotations.SerializedName

data class TagResponse(
    @SerializedName("accent") val accent: String?,
    @SerializedName("background_hash") val backgroundHash: String?,
    @SerializedName("background_is_animated") val backgroundIsAnimated: Boolean?,
    @SerializedName("description") val description: String?,
    @SerializedName("display_name") val displayName: String?,
    @SerializedName("followers") val followers: Int?,
    @SerializedName("following") val following: Boolean?,
    @SerializedName("is_promoted") val isPromoted: Boolean?,
    @SerializedName("is_whitelisted") val isWhitelisted: Boolean?,
    @SerializedName("items") val items: List<ImageResponse>?,
    @SerializedName("logo_destination_url") val logoDestinationUrl: Any?,
    @SerializedName("logo_hash") val logoHash: Any?,
    @SerializedName("name") val name: String?,
    @SerializedName("thumbnail_hash") val thumbnailHash: Any?,
    @SerializedName("thumbnail_is_animated") val thumbnailIsAnimated: Boolean?,
    @SerializedName("total_items") val totalItems: Int?
)