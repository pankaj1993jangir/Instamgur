package com.pj.instamgur.presentation.view.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.pj.instamgur.databinding.ItemGalleryBinding
import com.pj.instamgur.domain.entity.Feed

class GalleryViewPagerAdapter : RecyclerView.Adapter<GalleryViewPagerAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemGalleryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setViewModel(item: Feed) {
            binding.tvTitle.text = item.title
            binding.ivGallery.load(item.url)
        }
    }

    private var list: MutableList<Feed> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewPagerAdapter.ViewHolder, position: Int) {
        val item = list[position]
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