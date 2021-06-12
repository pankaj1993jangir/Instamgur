package com.pj.instamgur.data.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.imgur.com/3/"
private const val API_KEY = "16abb74c6e5c7e8"

class ImgurApiClient {
    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val oldRequest = chain.request()
                val newRequest = oldRequest.newBuilder()
                    .addHeader("Authorization", "Client-ID $API_KEY")
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api by lazy { retrofit.create(ImgurApiService::class.java) }

    companion object {
        private var INSTANCE: ImgurApiClient? = null
        fun getInstance() = INSTANCE
            ?: ImgurApiClient().also {
                INSTANCE = it
            }
    }
}


