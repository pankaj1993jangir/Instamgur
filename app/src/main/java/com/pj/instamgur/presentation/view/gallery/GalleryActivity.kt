package com.pj.instamgur.presentation.view.gallery

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.pj.instamgur.R
import com.pj.instamgur.databinding.ActivityGalleryBinding
import com.pj.instamgur.presentation.viewmodel.GalleryViewModel

private const val DEFAULT_AUTO_SCROLL_DURATION_IN_MILLI = 2000L

class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding
    private val viewModel: GalleryViewModel by viewModels()
    private var tag: String? = null
    private val galleryAdapter by lazy { GalleryViewPagerAdapter() }
    private val mainHandler by lazy { Handler(Looper.getMainLooper()) }

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
    }

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            mainHandler.removeCallbacks(swipeNextRunnable)
            mainHandler.postDelayed(swipeNextRunnable, DEFAULT_AUTO_SCROLL_DURATION_IN_MILLI)
        }
    }

    private val swipeNextRunnable = Runnable {
        binding.vpContainer.currentItem++
    }

    private fun setObserver() {
        viewModel.galleryFeed.observe(this) {
            galleryAdapter.updateItems(it)
        }
    }

    override fun onStart() {
        super.onStart()
        tag?.let {
            viewModel.fetchGalleryFeed(it)
        }
    }

    companion object {
        const val KEY_TAG = "tag"
    }
}