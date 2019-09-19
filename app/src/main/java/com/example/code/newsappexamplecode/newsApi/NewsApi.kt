package com.example.code.newsappexamplecode.newsApi

import com.example.code.newsappexamplecode.newsApi.newsData.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    fun getNewsHeadlines(
        @Query("country") country: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String
    ): Call<NewsResponse>
}