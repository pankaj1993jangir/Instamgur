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
                        val url = imageResponse.images[0].link
                        //if (!isVideoUrl(url)) {
                        url
                        /*}else {
                            imageResponse.images?.get(0)?.gifv
                        }*/
                    } else {
                        imageResponse.link
                    }
                if (!isVideoUrl(imageUrl)) {
                    imageUrl?.let {
                        mutableList.add(
                            Image(
                                imageResponse.id, imageResponse.title,
                                it
                            )
                        )
                    }
                }
            }
            mutableList
        } catch (e: Exception) {
            null
        }
    }

    fun isVideoUrl(url: String?): Boolean {
        return url?.endsWith("mp4") == true
    }

    companion object {
        private var INSTANCE: ImgurRepoImpl? = null
        fun getInstance() = INSTANCE
            ?: ImgurRepoImpl().also {
                INSTANCE = it
            }
    }
}