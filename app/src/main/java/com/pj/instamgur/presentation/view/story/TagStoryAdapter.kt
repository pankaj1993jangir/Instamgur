package com.pj.instamgur.presentation.view.story

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.pj.instamgur.databinding.ItemTagStoryBinding
import com.pj.instamgur.domain.entity.Tag
import com.pj.instamgur.presentation.view.gallery.GalleryActivity

class TagStoryAdapter : RecyclerView.Adapter<TagStoryAdapter.TagStoryViewHolder>() {

    class TagStoryViewHolder(private val binding: ItemTagStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setViewModel(item: Tag) {
            binding.tvTag.text = item.name
            binding.ivStory.load(item.url)
            binding.root.apply {
                setOnClickListener {
                    context.startActivity(Intent(context, GalleryActivity::class.java).apply {
                        putExtra(GalleryActivity.KEY_TAG, item.tag)
                    })
                }
            }
        }
    }

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
