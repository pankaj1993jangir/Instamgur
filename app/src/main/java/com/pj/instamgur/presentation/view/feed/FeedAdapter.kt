package com.pj.instamgur.presentation.view.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pj.instamgur.databinding.ItemFeedBinding
import com.pj.instamgur.domain.entity.Feed

class FeedAdapter : RecyclerView.Adapter<FeedViewHolder>() {

    private var list: MutableList<Feed> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding: ItemFeedBinding =
            ItemFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val item: Feed = list[position]
        holder.setViewModel(item)
    }

    fun updateItems(listItems: List<Feed>) {
        list = listItems.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}