package com.example.code.newsappexamplecode.fragmentProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.code.newsappexamplecode.Application
import com.example.code.newsappexamplecode.R
import com.example.code.newsappexamplecode.appComponents.abstractTypes.AbstractFragment
import com.example.code.newsappexamplecode.appSettings.AppSettings
import com.example.code.newsappexamplecode.fragmentProfile.adapterProfileList.AdapterProfileList
import com.example.code.newsappexamplecode.fragmentProfile.adapterProfileList.ItemProfileList
import com.example.code.newsappexamplecode.hideKeyboardFrom
import kotlinx.android.synthetic.main.fragment_profile.*
import java.util.ArrayList
import javax.inject.Inject

class ProfileFragment : AbstractFragment() {

    companion object {
        const val FRAGMENT_PROFILE = "profileFragment"
    }

    @Inject
    lateinit var settings: AppSettings
    private val data = ArrayList<ItemProfileList>()
    private lateinit var adapter: AdapterProfileList
    private lateinit var viewModel: VMProfileFragment
    private lateinit var liveData: LiveData<HashMap<String, Any>>


    override fun onCreate(savedInstanceState: Bundle?) {
        (activity?.application as Application).fragmentsComponent.inject(this)
        super.onCreate(savedInstanceState)

        PROFILE_KEYS.forEachIndexed { index, _ ->
            data.add(ItemProfileList("", getString(PROFILE_TITLES_RESOURCES[index])))
        }

        viewModel = ViewModelProviders.of(this).get(VMProfileFragment::class.java)
        liveData = viewModel.getData()
        liveData.observe(this, Observer<HashMap<String, Any>> {
            loadMode(false)
            toUserData(it)
            adapter.notifyDataSetChanged()

        })

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AdapterProfileList(ArrayList())
        adapter.data = data
        rvProfileList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvProfileList.adapter = adapter

        btnProfileConfirm.setOnClickListener {
            hideKeyboardFrom(requireContext(), view)
            viewModel.saveUserData(settings.currentUser?.email.toString(), fromUserData(data))
        }

    }


    override fun onStart() {
        super.onStart()
        loadMode(true)
        viewModel.loadUserData(settings.currentUser?.email.toString())
    }


    private fun loadMode(mode: Boolean) {
        if (mode) {
            pbProfile.visibility = View.VISIBLE
            btnProfileConfirm.isClickable = false
        } else {
            pbProfile.visibility = View.GONE
            btnProfileConfirm.isClickable = true
        }
    }

    private fun toUserData(dataValues: HashMap<String, Any>): ArrayList<ItemProfileList> {
        data.clear()
        PROFILE_KEYS.forEachIndexed { index, s ->
            data.add(
                ItemProfileList(
                    dataValues[s]?.toString() ?: "",
                    getString(PROFILE_TITLES_RESOURCES[index])
                )
            )
        }
        return data
    }


    private fun fromUserData(dataUser: ArrayList<ItemProfileList>): HashMap<String, Any> {
        val dataValues = HashMap<String, Any>()
        dataUser.forEachIndexed { index, itemProfileList ->
            dataValues[PROFILE_KEYS[index]] = itemProfileList.text
        }
        return dataValues
    }

}