package com.example.code.newsappexamplecode.userDataBase

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentSnapshot

class UserDataBase {

    companion object {
        const val TABLE_NAME = "users"
    }

    private val database = FirebaseFirestore.getInstance()


    fun writeUserData(
        userId: String,
        userData : HashMap<String, Any>,
        onSuccessListener: OnSuccessListener<Void>,
        onFailureListener: OnFailureListener) {
        database.collection(TABLE_NAME).document(userId)
            .set(userData)
            .addOnSuccessListener(onSuccessListener)
            .addOnFailureListener(onFailureListener)
    }

    fun readUserData(userId: String, onCompleteListener : OnCompleteListener<DocumentSnapshot>) {
        database.collection(TABLE_NAME).document(userId)
            .get()
            .addOnCompleteListener(onCompleteListener)
    }

}