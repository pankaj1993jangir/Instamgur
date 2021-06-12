package com.pj.instamgur.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.pj.instamgur.R
import com.pj.instamgur.mvvm.viewmodel.FeedViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: FeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.images.observe(this) {
            Log.e(MainActivity::class.java.simpleName, it.toString())
        }
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.fetchFeed("hot")
    }
}