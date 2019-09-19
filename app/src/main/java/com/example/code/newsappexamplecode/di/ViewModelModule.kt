package com.example.code.newsappexamplecode.di

import android.content.Context
import com.example.code.newsappexamplecode.newsApi.NewsService
import com.example.code.newsappexamplecode.userDataBase.UserDataBase
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule(var context: Context) {

    @Provides
    @ViewModelScope
    fun provideUserDataBase() = UserDataBase()

    @Provides
    @ViewModelScope
    fun provideNewsService() = NewsService(context)

}