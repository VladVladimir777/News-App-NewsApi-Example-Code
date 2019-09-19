package com.example.code.newsappexamplecode.fragmentNews.adapterNewsList

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.code.newsappexamplecode.newsApi.NewsService
import com.example.code.newsappexamplecode.newsApi.newsData.Article

class NewsDataSourceFactory(private val newsService: NewsService) :
    DataSource.Factory<Int, Article>() {

    var itemLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Article>>()


    override fun create(): DataSource<Int, Article> {
        val newsDataSource = NewsDataSource(newsService)
        itemLiveDataSource.postValue(newsDataSource)
        return newsDataSource
    }

}