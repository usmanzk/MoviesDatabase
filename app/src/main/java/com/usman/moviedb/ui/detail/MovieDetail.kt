package com.usman.moviedb.ui.detail

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.usman.moviedb.R
import com.usman.moviedb.databinding.ActivityMovieDetailBinding

class MovieDetail : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        binding.movieDetail = intent.getParcelableExtra("movie")
        binding.executePendingBindings()

    }
}
