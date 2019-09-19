package com.example.code.newsappexamplecode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.code.newsappexamplecode.appSettings.AppSettings
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var settings: AppSettings
    @Inject
    lateinit var userAuth: UserAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as Application).appComponent.inject(this)
        setContentView(R.layout.activity_splash)

        val currentUser = userAuth.getCurrentUser()
        settings.currentUser = currentUser

        if (currentUser != null) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        } else {
            startActivity(Intent(applicationContext, AuthenticationActivity::class.java))
            finish()
        }

    }

}
