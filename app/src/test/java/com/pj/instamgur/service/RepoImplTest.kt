package com.pj.instamgur.service

import com.pj.instamgur.data.ImgurRepoImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RepoImplTest {
    lateinit var repo: ImgurRepoImpl

    @Before
    fun init() {
        repo = ImgurRepoImpl.getInstance()
    }

    @Test
    fun `test generation of imageurl using hash`() {
        val hash = "hash"
        val url = "https://i.imgur.com/${hash}.jpg"
        Assert.assertEquals(url, repo.generateStoryImageUrl(hash))
    }

    @Test
    fun `is video`() {
        val videoUrl = "https://xyz.mp4"
        val imageUrl = "https://xyz.png"
        Assert.assertTrue(repo.isVideoUrl(videoUrl))
        Assert.assertFalse(repo.isVideoUrl(imageUrl))
    }
}