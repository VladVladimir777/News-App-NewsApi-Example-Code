package com.example.code.newsappexamplecode

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.app.Activity

class UserAuth {

    private val mAuth = FirebaseAuth.getInstance()


    fun getCurrentUser(): FirebaseUser? {
        return mAuth.currentUser
    }

    fun signUp(
        activity: Activity,
        email: String,
        password: String,
        onCompleteListener: OnCompleteListener<AuthResult>
    ) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity, onCompleteListener)
    }

    fun signIn(
        activity: Activity,
        email: String,
        password: String,
        onCompleteListener: OnCompleteListener<AuthResult>
    ) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity, onCompleteListener)
    }

    fun signOut() {
        mAuth.signOut()
    }

}