package com.example.movies.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.Constants.YOUTUBE_THUMBNAIL_END_URL
import com.example.movies.Constants.YOUTUBE_THUMBNAIL_START_URL
import com.example.movies.Constants.YOUTUBE_TRAILER_BASE_URL
import com.example.movies.R
import com.example.movies.model.MovieResult
import com.example.movies.model.Trailer

class TrailersAdapter (val trailerClickInterface: TrailerClickInterface): RecyclerView.Adapter<TrailersAdapter.TrailerViewHolder>() {
    var moviesTrailers: List<Trailer> = listOf<Trailer>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        return TrailersAdapter.TrailerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.trailers_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        holder.trailersTextView.text = moviesTrailers[position].name
        holder.bind(moviesTrailers[position])
        holder.itemView.setOnClickListener {
            trailerClickInterface.onMovieTrailerClick(moviesTrailers[position])
        }
    }

    override fun getItemCount(): Int {
       return moviesTrailers.size
    }

    class TrailerViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val trailersImageView = itemView.findViewById<ImageView>(R.id.trailer_image)
        var trailersTextView = itemView.findViewById<TextView>(R.id.trailer_name)
        val trailerPlayButtonImage= itemView.findViewById<ImageView>(R.id.play_button_image)

        fun bind( trailer: Trailer){
            Log.d("TAG", "showTrailers TRAILER " + trailer.name)
            val imageUrl = YOUTUBE_THUMBNAIL_START_URL + trailer.key + YOUTUBE_THUMBNAIL_END_URL

            imageUrl.let {
                val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
                Glide.with(trailersImageView.context)
                    .load(imageUri)
                    .into(trailersImageView)
            }

        }
    }

    interface TrailerClickInterface {
        fun onMovieTrailerClick(trailers: Trailer)
    }
}