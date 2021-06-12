package com.pj.instamgur.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.pj.instamgur.R
import com.pj.instamgur.databinding.ActivityMainBinding
import com.pj.instamgur.mvvm.viewmodel.FeedViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: FeedViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
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
}