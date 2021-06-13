package com.pj.instamgur.domain.repository

import com.pj.instamgur.domain.entity.Feed
import com.pj.instamgur.domain.entity.Tag

interface ImgurRepo {
    suspend fun getFeedList(feed: String): List<Feed>

    suspend fun getStoryTags() : List<Tag>

    suspend fun getGalleryFeed(tag: String): List<Feed>
}