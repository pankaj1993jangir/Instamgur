package com.pj.instamgur.presentation.view.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.pj.instamgur.R
import com.pj.instamgur.databinding.ActivityGalleryBinding
import com.pj.instamgur.presentation.viewmodel.GalleryViewModel

class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding
    private val viewModel: GalleryViewModel by viewModels()
    private var tag: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gallery)
        tag = intent.getStringExtra(KEY_TAG)
        setObserver()
    }

    private fun setObserver() {
        viewModel.galleryFeed.observe(this) {
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