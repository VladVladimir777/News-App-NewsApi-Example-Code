package com.example.code.newsappexamplecode.appComponents.abstractTypes

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.code.newsappexamplecode.appComponents.callbacks.FragmentsCallback

abstract class AbstractFragment : Fragment() {

    protected lateinit var fragmentCallback: FragmentsCallback


    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentCallback = activity as FragmentsCallback

    }
}