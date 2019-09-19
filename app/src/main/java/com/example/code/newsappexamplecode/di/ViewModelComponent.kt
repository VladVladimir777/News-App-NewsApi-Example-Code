package com.example.code.newsappexamplecode.di

import com.example.code.newsappexamplecode.fragmentNews.VMNewsFragment
import com.example.code.newsappexamplecode.fragmentProfile.VMProfileFragment
import dagger.Subcomponent

@Subcomponent(modules = [ViewModelModule::class])
@ViewModelScope
interface ViewModelComponent {

    fun inject(vmProfileFragment: VMProfileFragment)

    fun inject(vmNewsFragment: VMNewsFragment)

}