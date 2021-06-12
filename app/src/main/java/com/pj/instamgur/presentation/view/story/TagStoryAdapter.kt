package com.pj.instamgur.presentation.view.story

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pj.instamgur.databinding.ItemTagStoryBinding
import com.pj.instamgur.domain.entity.Tag

class TagStoryAdapter : RecyclerView.Adapter<TagStoryViewHolder>() {

    private var list: MutableList<Tag> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagStoryViewHolder {
        val binding =
            ItemTagStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TagStoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagStoryViewHolder, position: Int) {
        val item = list[position]
        holder.setViewModel(item)
    }

    fun updateItems(listItems: List<Tag>) {
        list = listItems.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
