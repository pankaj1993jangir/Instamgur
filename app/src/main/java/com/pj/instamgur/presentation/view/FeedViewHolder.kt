package com.pj.instamgur.presentation.view

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.pj.instamgur.databinding.ItemFeedBinding
import com.pj.instamgur.domain.entity.Image

class FeedViewHolder(private val binding: ItemFeedBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun setViewModel(item: Image) {
        binding.title.text = item.title
        binding.userName.text = item.id
        binding.image.load(item.url)
    }
}