package com.pj.instamgur.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pj.instamgur.data.ImgurRepoImpl
import com.pj.instamgur.domain.entity.Feed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryViewModel : ViewModel() {
    private val imgurRepo by lazy { ImgurRepoImpl.getInstance() }

    private val _galleryFeed = MutableLiveData<List<Feed>>()
    val galleryFeed: LiveData<List<Feed>> = _galleryFeed

    fun fetchGalleryFeed(tag: String) = viewModelScope.launch(Dispatchers.IO) {
        _galleryFeed.postValue(imgurRepo.getGalleryFeed(tag))
    }
}