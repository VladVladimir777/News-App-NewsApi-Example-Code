package com.example.code.newsappexamplecode.fragmentNews

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.code.newsappexamplecode.newsApi.NewsService
import com.example.code.newsappexamplecode.newsApi.PAGE_SIZE
import com.example.code.newsappexamplecode.newsApi.newsData.Article
import javax.inject.Inject
import com.example.code.newsappexamplecode.fragmentNews.adapterNewsList.NewsDataSourceFactory

class VMNewsFragment(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var newsService: NewsService
    private var data : LiveData<PagedList<Article>>

    init {
        (application as com.example.code.newsappexamplecode.Application).viewModelComponent.inject(
            this
        )
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE).build()
        data = LivePagedListBuilder(NewsDataSourceFactory(newsService), pagedListConfig).build()
    }


    fun getData(): LiveData<PagedList<Article>> {
        return data
    }

}