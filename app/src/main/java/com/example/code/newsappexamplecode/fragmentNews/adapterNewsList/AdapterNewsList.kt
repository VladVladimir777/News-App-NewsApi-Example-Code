package com.example.code.newsappexamplecode.fragmentNews.adapterNewsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.code.newsappexamplecode.newsApi.newsData.Article
import kotlinx.android.synthetic.main.item_news_list.view.*
import androidx.recyclerview.widget.DiffUtil
import com.example.code.newsappexamplecode.R

class AdapterNewsList(clickListener: ItemClickListener? = null) :
    PagedListAdapter<Article, AdapterNewsList.ViewHolder>(diffUtilCallback) {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_news_list, viewGroup, false)
        )
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        getItem(i)?.let { viewHolder.bind(it) }
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Article) = with(itemView) {
            Glide
                .with(itemView)
                .load(item.urlToImage)
                .centerCrop()
                .into(itemView.ivNewsItemImage)
            itemView.tvNewsItemSource.text = item.source?.name
            itemView.tvNewsItemTitle.text = item.title
        }
    }


    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }


    companion object {
        val diffUtilCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }

}