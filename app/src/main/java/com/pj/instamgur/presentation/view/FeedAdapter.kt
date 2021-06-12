package com.pj.instamgur.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pj.instamgur.databinding.ItemFeedBinding
import com.pj.instamgur.domain.entity.Image

class FeedAdapter : RecyclerView.Adapter<FeedViewHolder>() {

    private var list: MutableList<Image> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding: ItemFeedBinding =
            ItemFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val item: Image = list[position]
        holder.setViewModel(item)
    }

    fun updateItems(listItems: List<Image>) {
        list = listItems.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}