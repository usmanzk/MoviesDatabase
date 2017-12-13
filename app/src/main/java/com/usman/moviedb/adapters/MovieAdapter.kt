package com.usman.moviedb.adapters

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.usman.moviedb.BR
import com.usman.moviedb.R
import com.usman.moviedb.models.Movies
import com.usman.moviedb.ui.detail.MovieDetail

/**
 * Created by HP on 12/13/2017.
 */
class MovieAdapter(movies: ArrayList<Movies>, val context: Context) : RecyclerView.Adapter<MoviesViewHolder>() {

    var movies: ArrayList<Movies> = movies

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder?, position: Int) {

        (holder as MoviesViewHolder).bind(movies[holder.adapterPosition])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MoviesViewHolder {

        val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_recycler_movie, parent, false)
        return MoviesViewHolder(binding)
    }

    fun setData(filteredMovies: ArrayList<Movies>) {
        movies = filteredMovies
        notifyDataSetChanged()
    }

}

class MoviesViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movies) {
        binding.setVariable(BR.movieData, movie)
        binding.root.setOnClickListener { v -> goToDetail(movie, v.context) }
        binding.executePendingBindings()
    }

    private fun goToDetail(movie: Movies, context: Context) {
        val i = Intent(context, MovieDetail::class.java)
        i.putExtra("movie", movie)
        context.startActivity(i)
    }

}
