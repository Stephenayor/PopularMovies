package com.example.movies.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.movies.R
import com.example.movies.databinding.FragmentPopularMoviesDetailBinding
import com.example.movies.model.MovieResult

class PopularMoviesDetailFragment : Fragment() {
    lateinit var binding: FragmentPopularMoviesDetailBinding
    lateinit var movieResult: MovieResult


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentPopularMoviesDetailBinding>(
            inflater, R.layout.fragment_popular_movies_detail, container,
            false)
        binding.root.setBackgroundColor(Color.WHITE)

        val bundle = this.arguments
        if (bundle != null) {
            movieResult =
                bundle.getParcelable("movies")!!
        } else {
            throw NullPointerException("Movies Detail Fragment must receive a movie parcel")
        }
        return binding.root
    }

}