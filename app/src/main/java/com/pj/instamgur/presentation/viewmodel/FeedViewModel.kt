package com.pj.instamgur.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pj.instamgur.data.ImgurRepoImpl
import com.pj.instamgur.domain.entity.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {
    private val _images = MutableLiveData<List<Image>>()
    val images: LiveData<List<Image>> = _images

    private val imgurRepo by lazy { ImgurRepoImpl.getInstance() }

    fun fetchFeed(feed: String) = viewModelScope.launch(Dispatchers.IO) {
        when (feed) {
            "top" -> _images.postValue(imgurRepo.getFeedList("top"))
            "hot" -> _images.postValue(imgurRepo.getFeedList("hot"))
            else -> _images.postValue(imgurRepo.getFeedList("top"))

        }
    }
}