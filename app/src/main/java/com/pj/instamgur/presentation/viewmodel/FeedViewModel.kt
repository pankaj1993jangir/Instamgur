package com.pj.instamgur.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pj.instamgur.data.ImgurRepoImpl
import com.pj.instamgur.domain.entity.Feed
import com.pj.instamgur.domain.entity.Tag
import com.pj.instamgur.presentation.enum.FeedType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {

    private val imgurRepo by lazy { ImgurRepoImpl.getInstance() }

    private val _tags = MutableLiveData<List<Tag>>()
    val tags: LiveData<List<Tag>> = _tags

    private val _feed = MutableLiveData<List<Feed>>()
    val feed: LiveData<List<Feed>> = _feed

    fun fetchFeed(feed: String) = viewModelScope.launch(Dispatchers.IO) {
        when (feed) {
            FeedType.TOP.name -> _feed.postValue(imgurRepo.getFeedList("top"))
            FeedType.HOT.name -> _feed.postValue(imgurRepo.getFeedList("hot"))
            else -> _feed.postValue(imgurRepo.getFeedList("top"))
        }
    }

    fun fetchTags() = viewModelScope.launch(Dispatchers.IO) {
        _tags.postValue(imgurRepo.getStoryTags())
    }
}