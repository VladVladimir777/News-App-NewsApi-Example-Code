package com.example.code.newsappexamplecode.fragmentProfile

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.code.newsappexamplecode.R
import com.example.code.newsappexamplecode.userDataBase.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import javax.inject.Inject

class VMProfileFragment(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var userDataBase: UserDataBase
    private var data = MutableLiveData<HashMap<String, Any>>()

    init {
        (application as com.example.code.newsappexamplecode.Application).viewModelComponent.inject(
            this
        )
    }


    fun getData(): MutableLiveData<HashMap<String, Any>> {
        return data
    }

    fun loadUserData(userId: String) {
        userDataBase.readUserData(userId, OnCompleteListener {
            if (it.isSuccessful) {
                val dataValues = HashMap<String, Any>()
                it.result?.let { value ->
                    value.get(FIRST_NAME)?.let { it1 -> dataValues.put(FIRST_NAME, it1) }
                }
                it.result?.let { value ->
                    value.get(LAST_NAME)?.let { it1 -> dataValues.put(LAST_NAME, it1) }
                }
                it.result?.let { value ->
                    value.get(PHONE_NUMBER)?.let { it1 -> dataValues.put(PHONE_NUMBER, it1) }
                }
                it.result?.let { value ->
                    value.get(COUNTRY)?.let { it1 -> dataValues.put(COUNTRY, it1) }
                }
                it.result?.let { value ->
                    value.get(CITY)?.let { it1 -> dataValues.put(CITY, it1) }
                }
                data.postValue(dataValues)
            }
        })
    }

    fun saveUserData(userId: String, userData: HashMap<String, Any>) {
        userDataBase.writeUserData(userId, userData,
            OnSuccessListener {
                Toast.makeText(
                    getApplication(),
                    R.string.fragment_profile_change_saved,
                    Toast.LENGTH_SHORT
                ).show()

            }, OnFailureListener {
                Toast.makeText(
                    getApplication(),
                    getApplication<Application>().getString(
                        R.string.fragment_profile_change_not_saved,
                        it.message
                    ),
                    Toast.LENGTH_SHORT
                ).show()
            })
    }

}