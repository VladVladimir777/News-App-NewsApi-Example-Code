package com.example.code.newsappexamplecode.newsApi

import android.content.Context
import com.example.code.newsappexamplecode.getLocale
import com.example.code.newsappexamplecode.newsApi.newsData.NewsResponse
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsService(val context: Context) {

    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val getNewsApi: NewsApi = retrofit.create(NewsApi::class.java)


    fun loadNews(
        country: String = getLocale(context).country,
        pageSize: Int = PAGE_SIZE,
        page: Int = 1,
        apiKey: String = API_KEY,
        callback: Callback<NewsResponse>
    ) {
        getNewsApi.getNewsHeadlines(country, pageSize, page, apiKey).enqueue(callback)
    }

}