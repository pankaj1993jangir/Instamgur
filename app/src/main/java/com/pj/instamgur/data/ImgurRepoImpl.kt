package com.pj.instamgur.data

import com.pj.instamgur.data.service.ImgurApiClient
import com.pj.instamgur.domain.entity.Image
import com.pj.instamgur.domain.entity.repository.ImgurRepo
import java.lang.Exception

class ImgurRepoImpl : ImgurRepo {
    val api by lazy { ImgurApiClient.getInstance().api }
    override suspend fun getFeedList(feed: String): List<Image>? {
        return try {
            val mutableList: MutableList<Image> = ArrayList()
            api.getFeedResponse(feed).data?.map { imageResponse ->
                val imageUrl =
                    if (imageResponse.isAlbum == true && imageResponse.images != null) {
                        val firstImage = imageResponse.images?.get(0)
                        firstImage?.link
                    } else {
                        imageResponse.link
                    }
                imageUrl?.let {
                    mutableList.add(
                        Image(
                            imageResponse.id, imageResponse.title,
                            it
                        )
                    )
                }
            }
            mutableList
        } catch (e: Exception) {
            null
        }
    }

    companion object {
        private var INSTANCE: ImgurRepoImpl? = null
        fun getInstance() = INSTANCE
            ?: ImgurRepoImpl().also {
                INSTANCE = it
            }
    }
}