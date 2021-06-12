package com.pj.instamgur.presentation.view.story

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.pj.instamgur.databinding.ItemTagStoryBinding
import com.pj.instamgur.domain.entity.Tag

class TagStoryViewHolder(private val binding: ItemTagStoryBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun setViewModel(item: Tag) {
        binding.tvTag.text = item.name
        binding.ivStory.load(item.url)
    }
}