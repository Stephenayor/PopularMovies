package com.example.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.model.MovieResult

class TopRatedMoviesAdapter() : RecyclerView.Adapter<TopRatedMoviesAdapter.TopRatedMoviesViewHolder>() {
    var movies: List<MovieResult> = listOf<MovieResult>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedMoviesViewHolder {
        return TopRatedMoviesViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.toprated_movies_imageitem, parent, false))
    }

    override fun onBindViewHolder(holder: TopRatedMoviesViewHolder, position: Int) {
        Glide.with(holder.imageView.context)
                .load("https://image.tmdb.org/t/p/w500/" +
                        movies[position].posterPath)
                .into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class TopRatedMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView = itemView.findViewById<ImageView>(R.id.topRatedMovies_image_view)

    }

}