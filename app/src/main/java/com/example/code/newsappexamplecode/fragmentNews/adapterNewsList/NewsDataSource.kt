package com.example.code.newsappexamplecode.fragmentNews.adapterNewsList

import androidx.paging.PageKeyedDataSource
import com.example.code.newsappexamplecode.newsApi.NewsService
import com.example.code.newsappexamplecode.newsApi.newsData.Article
import com.example.code.newsappexamplecode.newsApi.newsData.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsDataSource(private val newsService: NewsService) : PageKeyedDataSource<Int, Article>() {

    private var firstPage = 1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Article>
    ) {
        newsService.loadNews(
            callback = object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    response.body()?.articles?.let { callback.onResult(it, null, firstPage + 1); }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

                }
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        newsService.loadNews(
            page = params.key,
            callback = object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    response.body()?.articles?.let { callback.onResult(it, params.key + 1); }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

                }
            })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        newsService.loadNews(
            page = params.key,
            callback = object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    val adjacentKey = (if (params.key > 1) params.key - 1 else null)?.toInt()
                    response.body()?.articles?.let { callback.onResult(it, adjacentKey); }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

                }
            })
    }

}