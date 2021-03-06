package com.pj.instamgur.presentation.view.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pj.instamgur.R
import com.pj.instamgur.data.util.NetworkUtil
import com.pj.instamgur.databinding.FragmentFeedBinding
import com.pj.instamgur.presentation.view.feed.FeedAdapter
import com.pj.instamgur.presentation.enum.FeedType
import com.pj.instamgur.presentation.viewmodel.FeedViewModel

class FeedFragment : Fragment() {

    private val viewModel: FeedViewModel by viewModels()
    private lateinit var feedType: String
    private lateinit var binding: FragmentFeedBinding
    private val feedAdapter by lazy {
        FeedAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedType =
            arguments?.getString(resources.getString(R.string.arg_feed_type)) ?: FeedType.TOP.name
        if (NetworkUtil.isNetworkAvailable(requireContext())) {
            viewModel.fetchFeed(feedType)
        } else {
            Toast.makeText(requireContext(), R.string.connet_internet, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        binding.rvFeed.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFeed.adapter = feedAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.feed.observe(viewLifecycleOwner) {
            feedAdapter.updateItems(it)
        }
    }
}