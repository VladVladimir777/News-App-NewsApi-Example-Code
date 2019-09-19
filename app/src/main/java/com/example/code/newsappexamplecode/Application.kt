package com.example.code.newsappexamplecode

import androidx.multidex.MultiDexApplication
import com.example.code.newsappexamplecode.di.*

class Application : MultiDexApplication() {

    lateinit var appComponent: AppComponent
    lateinit var fragmentsComponent: FragmentsComponent
    lateinit var viewModelComponent: ViewModelComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        fragmentsComponent = appComponent.plusFragmentsComponent(FragmentsModule(this))

        viewModelComponent = appComponent.plusViewModelComponent(ViewModelModule(this))

    }

}