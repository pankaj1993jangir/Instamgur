package com.pj.instamgur.domain.entity.repository

import com.pj.instamgur.domain.entity.Image

interface ImgurRepo {
    suspend fun getFeedList(feed: String): List<Image>?

    suspend fun getStoryTags():
}