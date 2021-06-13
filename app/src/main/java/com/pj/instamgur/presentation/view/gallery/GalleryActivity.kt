package com.pj.instamgur.presentation.view.gallery

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.pj.instamgur.R
import com.pj.instamgur.databinding.ActivityGalleryBinding
import com.pj.instamgur.presentation.viewmodel.GalleryViewModel

private const val DEFAULT_AUTO_SCROLL_DURATION_IN_MILLI = 3000L

class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding
    private val viewModel: GalleryViewModel by viewModels()
    private var tag: String? = null
    private val galleryAdapter by lazy { GalleryViewPagerAdapter() }
    private var pageIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gallery)
        tag = intent.getStringExtra(KEY_TAG)
        setGalleryViewPager()
        setObserver()
    }

    private fun setGalleryViewPager() {
        binding.vpContainer.adapter = galleryAdapter
        binding.vpContainer.registerOnPageChangeCallback(pageChangeCallback)
        binding.pvStoryTimer.setStoriesListener(object : GalleryProgressView.StoriesListener {
            override fun onNext() {
                binding.vpContainer.currentItem++
            }

            override fun onComplete() {
                // TODO- Move to next tag
            }

        })
    }

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            if (pageIndex > position) {
                binding.pvStoryTimer.startPrev()
            }else{
                binding.pvStoryTimer.startNext()
            }
            pageIndex = position
        }
    }

    private fun setObserver() {
        viewModel.galleryFeed.observe(this) {
            galleryAdapter.updateItems(it)
            binding.pvStoryTimer.setStoriesCount(it.size)
            binding.pvStoryTimer.setStoryDuration(DEFAULT_AUTO_SCROLL_DURATION_IN_MILLI)
        }
    }

    override fun onStart() {
        super.onStart()
        tag?.let {
            viewModel.fetchGalleryFeed(it)
        }
    }

    override fun onDestroy() {
        binding.pvStoryTimer.destroy()
        super.onDestroy()
    }

    companion object {
        const val KEY_TAG = "tag"
    }
}