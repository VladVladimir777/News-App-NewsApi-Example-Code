package com.example.code.newsappexamplecode.di

import com.example.code.newsappexamplecode.AuthenticationActivity
import com.example.code.newsappexamplecode.MainActivity
import com.example.code.newsappexamplecode.SplashActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun plusFragmentsComponent(fragmentsModule: FragmentsModule): FragmentsComponent

    fun plusViewModelComponent(viewModelModule: ViewModelModule): ViewModelComponent

    fun inject(mainActivity: MainActivity)

    fun inject(authenticationActivity: AuthenticationActivity)

    fun inject(splashActivity: SplashActivity)

}