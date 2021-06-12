package com.pj.instamgur.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pj.instamgur.R
import com.pj.instamgur.databinding.ActivityMainBinding
import com.pj.instamgur.presentation.view.story.TagStoryAdapter
import com.pj.instamgur.presentation.viewmodel.FeedViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: FeedViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val tagStoryAdapter by lazy { TagStoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupNavigationBar()
        setUpTagStoryRecyclerView()
        setUpObserver()
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchTags()
    }

    private fun setUpObserver() {
        viewModel.tags.observe(this, {
            tagStoryAdapter.updateItems(it)
        })
    }

    private fun setupNavigationBar() {
        val navController = findNavController(R.id.fragment_nav_container)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_top,
                R.id.nav_hot,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bnvFeedBar.setupWithNavController(navController)
    }

    private fun setUpTagStoryRecyclerView() {
        binding.rvStoryTags.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = tagStoryAdapter
        }
    }
}