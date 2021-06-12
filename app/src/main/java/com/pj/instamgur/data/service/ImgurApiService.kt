package com.pj.instamgur.data.service

import com.pj.instamgur.data.entity.FeedResponse
import com.pj.instamgur.data.entity.TagResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ImgurApiService {

    @GET("gallery/{feed}")
    suspend fun getFeedResponse(
        @Path("feed") feed: String
    ): FeedResponse


    @GET("tags")
    suspend fun getTags(): TagResponse
}