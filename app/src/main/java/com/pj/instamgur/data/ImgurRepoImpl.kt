package com.pj.instamgur.data

import androidx.annotation.VisibleForTesting
import com.pj.instamgur.data.service.ImgurApiClient
import com.pj.instamgur.domain.entity.Feed
import com.pj.instamgur.domain.entity.Tag
import com.pj.instamgur.domain.repository.ImgurRepo
import java.lang.Exception

class ImgurRepoImpl : ImgurRepo {
    private val api by lazy { ImgurApiClient.getInstance().api }
    override suspend fun getFeedList(feed: String): List<Feed> {
        val mutableList: MutableList<Feed> = ArrayList()
        return try {
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
                            Feed(
                                imageResponse.id, imageResponse.title,
                                it
                            )
                        )
                    }
                }
            }
            mutableList
        } catch (e: Exception) {
            mutableList
        }
    }

    override suspend fun getStoryTags(): List<Tag> {
        val mutableList: MutableList<Tag> = ArrayList()
        return try {
            api.getTags().data.tags.map { tagResponse ->
                if (tagResponse.displayName != null && tagResponse.backgroundHash != null
                    && tagResponse.name != null
                ) {
                    mutableList.add(
                        Tag(
                            tagResponse.name,
                            tagResponse.displayName,
                            generateStoryImageUrl(tagResponse.backgroundHash)
                        )
                    )
                }
            }
            mutableList
        } catch (e: Exception) {
            ArrayList()
        }
    }

    override suspend fun getGalleryFeed(tag: String): List<Feed> {
        val mutableList: MutableList<Feed> = ArrayList()
        return try {
            api.getTagGallery(tag).data?.items?.map { imageResponse ->
                val imageUrl =
                    if (imageResponse.isAlbum == true && imageResponse.images != null) {
                        val url = imageResponse.images[0].link
                        url
                    } else {
                        imageResponse.link
                    }
                if (!isVideoUrl(imageUrl)) {
                    imageUrl?.let {
                        mutableList.add(
                            Feed(
                                imageResponse.id, imageResponse.title,
                                it
                            )
                        )
                    }
                }
            }
            mutableList
        } catch (e: Exception) {
            ArrayList()
        }
    }

    @VisibleForTesting
    fun generateStoryImageUrl(hash: String): String {
        return "https://i.imgur.com/${hash}.jpg"
    }

    @VisibleForTesting
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