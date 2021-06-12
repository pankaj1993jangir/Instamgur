package com.pj.instamgur.api

import com.pj.instamgur.data.service.ImgurApiClient
import com.pj.instamgur.data.service.ImgurApiService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ImgurApiTest {

    private lateinit var apiService: ImgurApiService

    @Before
    fun setup() {
        apiService = ImgurApiClient.getInstance().api
    }

    @Test
    fun `validate feed response`() {

        runBlocking {
            val response = apiService.getFeedResponse("hot")
            println(response)
            Assert.assertNotNull(response)
        }
    }

    @Test
    fun `validate tag response`() {

        runBlocking {
            val response = apiService.getTags()
            println(response)
            Assert.assertNotNull(response)
        }
    }
}