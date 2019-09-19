package com.example.code.newsappexamplecode.newsApi.newsData

data class NewsResponse(
    var status: String? = null,
    var totalResults: Float = 0F,
    var articles: ArrayList<Article> = ArrayList()
)