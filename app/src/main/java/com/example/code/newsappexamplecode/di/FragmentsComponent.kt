package com.example.code.newsappexamplecode.di

import com.example.code.newsappexamplecode.fragmentNews.NewsFragment
import com.example.code.newsappexamplecode.fragmentProfile.ProfileFragment
import dagger.Subcomponent

@Subcomponent(modules = [FragmentsModule::class])
@FragmentsScope
interface FragmentsComponent {

    fun inject(profileFragment: ProfileFragment)

    fun inject(newsFragment: NewsFragment)

}