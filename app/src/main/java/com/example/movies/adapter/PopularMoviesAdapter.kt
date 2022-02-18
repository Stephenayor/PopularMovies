package com.example.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.model.MovieResult

class PopularMoviesAdapter() : RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>() {
    var movies: List<MovieResult> = listOf<MovieResult>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        return PopularMoviesViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.popular_movies_image_item, parent, false))
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        Glide.with(holder.imageView.context)
                .load("https://image.tmdb.org/t/p/w500/" +
                        movies[position].posterPath)
                .into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class PopularMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

         val imageView = itemView.findViewById<ImageView>(R.id.popularMovies_image_view)


    }

}