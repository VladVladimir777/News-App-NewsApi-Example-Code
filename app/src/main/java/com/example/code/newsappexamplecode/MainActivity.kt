package com.example.code.newsappexamplecode

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.code.newsappexamplecode.appComponents.abstractTypes.AbstractActivity
import com.example.code.newsappexamplecode.appComponents.callbacks.FragmentsCallback
import com.example.code.newsappexamplecode.appSettings.AppSettings
import com.example.code.newsappexamplecode.fragmentNews.NewsFragment
import com.example.code.newsappexamplecode.fragmentProfile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_header.view.*
import javax.inject.Inject

class MainActivity : AbstractActivity(), FragmentsCallback {

    @Inject
    lateinit var settings: AppSettings
    @Inject
    lateinit var userAuth: UserAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as Application).appComponent.inject(this)
        setContentView(R.layout.activity_main)

        navigationView.getHeaderView(0).tvHeaderTitle.text = userAuth.getCurrentUser()?.email
        navigationView.itemIconTintList = null
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isCheckable = false
            drawerLayout.closeDrawers()
            when (menuItem.itemId) {
                R.id.nav_item_news -> {
                    setFragment(NewsFragment.FRAGMENT_NEWS)
                }
                R.id.nav_item_profile -> {
                    setFragment(ProfileFragment.FRAGMENT_PROFILE)
                }
                R.id.nav_item_sign_out -> {
                    userAuth.signOut()
                    startActivity(Intent(applicationContext, AuthenticationActivity::class.java))
                    finish()
                }
            }
            true
        }

        ibToolbarMenu.setOnClickListener { drawerLayout.openDrawer(navigationView, true) }


        if (savedInstanceState == null) {
            setFragment(NewsFragment.FRAGMENT_NEWS)
        } else {
            when (supportFragmentManager.findFragmentById(R.id.flMain)) {
                is NewsFragment -> setTitle(NewsFragment.FRAGMENT_NEWS)
                is ProfileFragment -> setTitle(ProfileFragment.FRAGMENT_PROFILE)
            }
        }

    }


    override fun setFragment(key: String) {
        val frTransaction = supportFragmentManager.beginTransaction()
        when (key) {
            ProfileFragment.FRAGMENT_PROFILE ->
                if (supportFragmentManager.findFragmentById(R.id.flMain) !is ProfileFragment) {
                    setTitle(key)
                    frTransaction.replace(R.id.flMain, ProfileFragment())
                }
            NewsFragment.FRAGMENT_NEWS ->
                if (supportFragmentManager.findFragmentById(R.id.flMain) !is NewsFragment) {
                    setTitle(key)
                    frTransaction.replace(R.id.flMain, NewsFragment())
                }
        }
        frTransaction.commit()
    }

    private fun setTitle(key: String) {
        when (key) {
            NewsFragment.FRAGMENT_NEWS -> {
                tvToolbar.setText(R.string.fragment_news_title)
            }
            ProfileFragment.FRAGMENT_PROFILE -> {
                tvToolbar.setText(R.string.fragment_profile_title)
            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentById(R.id.flMain) !is NewsFragment) {
            setFragment(NewsFragment.FRAGMENT_NEWS)
        }
    }

}
