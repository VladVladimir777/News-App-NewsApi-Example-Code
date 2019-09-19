package com.example.code.newsappexamplecode.appComponents.abstractTypes

import androidx.appcompat.app.AppCompatActivity
import com.example.code.newsappexamplecode.appComponents.callbacks.FragmentsCallback

abstract class AbstractActivity : AppCompatActivity() {

    protected lateinit var fragmentCallback: FragmentsCallback


    override fun onResume() {
        super.onResume()
        fragmentCallback = this as FragmentsCallback
    }
}