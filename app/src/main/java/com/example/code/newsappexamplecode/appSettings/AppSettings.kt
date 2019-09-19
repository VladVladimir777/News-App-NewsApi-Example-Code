package com.example.code.newsappexamplecode.appSettings

import android.content.Context
import com.google.firebase.auth.FirebaseUser
import com.orhanobut.hawk.Hawk

class AppSettings(context: Context) {

    init {
        Hawk.init(context).build()
    }

    var currentUser: FirebaseUser?
        get() = Hawk.get(SETTING_KEY_CURRENT_USER, null)
        set(value) {
            Hawk.put(SETTING_KEY_CURRENT_USER, value)
        }

}