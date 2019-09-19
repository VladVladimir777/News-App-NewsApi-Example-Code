package com.example.code.newsappexamplecode.fragmentNews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.code.newsappexamplecode.R
import com.example.code.newsappexamplecode.appComponents.abstractTypes.AbstractFragment
import com.example.code.newsappexamplecode.fragmentNews.adapterNewsList.AdapterNewsList
import com.example.code.newsappexamplecode.newsApi.newsData.Article
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : AbstractFragment(), AdapterNewsList.ItemClickListener {

    companion object {
        const val FRAGMENT_NEWS = "newsFragment"
    }

    private lateinit var adapter: AdapterNewsList
    private lateinit var viewModel: VMNewsFragment
    private lateinit var liveData: LiveData<PagedList<Article>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(VMNewsFragment::class.java)
        liveData = viewModel.getData()
        liveData.observe(this, Observer<PagedList<Article>> {
            loadMode(false)
            adapter.submitList(it)
        })

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AdapterNewsList(null)
        rvNewsList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvNewsList.adapter = adapter

        loadMode(true)

    }

    override fun onStart() {
        super.onStart()

    }


    override fun onItemClick(view: View, position: Int) {

    }


    private fun loadMode(mode: Boolean) {
        if (mode) {
            pbNews.visibility = View.VISIBLE
        } else {
            pbNews.visibility = View.GONE
        }
    }

}