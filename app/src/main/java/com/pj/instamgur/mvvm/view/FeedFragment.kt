package com.pj.instamgur.mvvm.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pj.instamgur.R
import com.pj.instamgur.databinding.FragmentFeedBinding
import com.pj.instamgur.mvvm.enum.FeedType
import com.pj.instamgur.mvvm.viewmodel.FeedViewModel

class FeedFragment : Fragment() {

    private val viewModel: FeedViewModel by viewModels()
    private lateinit var feedType: String
    private lateinit var binding: FragmentFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedType =
            arguments?.getString(resources.getString(R.string.arg_feed_type)) ?: FeedType.TOP.name
        viewModel.fetchFeed(feedType)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.images.observe(viewLifecycleOwner) {
            Log.e("RESPONSE", it.toString())
        }
    }
}