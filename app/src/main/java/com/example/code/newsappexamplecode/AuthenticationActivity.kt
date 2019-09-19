package com.example.code.newsappexamplecode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.code.newsappexamplecode.appSettings.AppSettings
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.android.synthetic.main.activity_authentication.*
import javax.inject.Inject

class AuthenticationActivity : AppCompatActivity() {

    @Inject
    lateinit var settings: AppSettings
    @Inject
    lateinit var userAuth: UserAuth
    private var modeAuth = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as Application).appComponent.inject(this)
        setContentView(R.layout.activity_authentication)

        btnAuthEnter.setOnClickListener {
            currentFocus?.let { hideKeyboardFrom(this, it) }
            if (modeAuth) signUp() else signIn()
        }

        btnAuthRegistration.setOnClickListener { authMode(true) }

    }


    private fun authMode(mode: Boolean) {
        modeAuth = mode
        if (mode) {
            etAuthPassConfirmation.visibility = View.VISIBLE
            btnAuthEnter.text = getString(R.string.activity_authentication_sign_up)
            btnAuthRegistration.visibility = View.GONE
        } else {
            etAuthPassConfirmation.visibility = View.GONE
            btnAuthEnter.text = getString(R.string.activity_authentication_sign_in)
            btnAuthRegistration.visibility = View.VISIBLE
        }
    }

    private fun signIn() {
        val userEmail = etAuthEmail.text.toString().trim()
        val userPass = etAuthPassword.text.toString().trim()
        if (validateEmail(userEmail)) {
            if (userPass.length >= 6) {
                pbAuth.visibility = View.VISIBLE
                userAuth.signIn(this, userEmail, userPass, OnCompleteListener {
                    if (it.isSuccessful) {
                        pbAuth.visibility = View.GONE
                        settings.currentUser = userAuth.getCurrentUser()
                        startMainActivity()
                    } else {
                        pbAuth.visibility = View.GONE
                        showToastAuthFailed()
                    }
                })
            } else {
                showToastPasswordEmpty()
            }
        } else {
            showToastEmailNotValid()
        }
    }

    private fun signUp() {
        val userEmail = etAuthEmail.text.toString().trim()
        val userPass = passConfirm(etAuthPassword.text.toString().trim(),
            etAuthPassConfirmation.text.toString().trim())

        if (validateEmail(userEmail)) {
            if (userPass.length >= 6) {
                pbAuth.visibility = View.VISIBLE
                userAuth.signUp(this, userEmail, userPass, OnCompleteListener {
                    if (it.isSuccessful) {
                        pbAuth.visibility = View.GONE
                        settings.currentUser = userAuth.getCurrentUser()
                        startMainActivity()
                    } else {
                        pbAuth.visibility = View.GONE
                        showToastAuthFailed()
                    }
                })
            } else {
                showToastPasswordEmpty()
            }
        } else {
            showToastEmailNotValid()
        }
    }


    private fun startMainActivity() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }

    private fun passConfirm(pass1: String, pass2: String) : String {
        val confirm = pass1.isNotEmpty() && pass2.isNotEmpty() && pass1 == (pass2)
        return if (confirm) pass1 else ""
    }

    private fun showToastEmailNotValid() {
        Toast.makeText(
            this,
            R.string.activity_authentication_email_not_valid, Toast.LENGTH_SHORT
        ).show()
    }

    private fun showToastPasswordEmpty() {
        Toast.makeText(
            this,
            R.string.activity_authentication_password_error, Toast.LENGTH_SHORT
        ).show()
    }

    private fun showToastAuthFailed() {
        Toast.makeText(
            this,
            R.string.activity_authentication_failed, Toast.LENGTH_SHORT
        ).show()
    }

}
