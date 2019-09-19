package com.example.code.newsappexamplecode.di

import android.content.Context
import com.example.code.newsappexamplecode.UserAuth
import com.example.code.newsappexamplecode.appSettings.AppSettings
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var context: Context) {


    @Provides
    @Singleton
    fun provideSettings() = AppSettings(context)

    @Provides
    @Singleton
    fun provideUserAuth() = UserAuth()

}