package com.pj.instamgur.presentation.view.feed

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.pj.instamgur.databinding.ItemFeedBinding
import com.pj.instamgur.domain.entity.Feed

class FeedViewHolder(private val binding: ItemFeedBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun setViewModel(item: Feed) {
        binding.title.text = item.title
        binding.image.load(item.url)
    }
}