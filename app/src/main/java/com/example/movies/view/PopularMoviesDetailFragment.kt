package com.example.movies.view

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movies.Constants.YOUTUBE_TRAILER_BASE_URL
import com.example.movies.R
import com.example.movies.adapter.TrailersAdapter
import com.example.movies.base.BaseFragment
import com.example.movies.databinding.FragmentMainBinding
import com.example.movies.databinding.FragmentPopularMoviesDetailBinding
import com.example.movies.model.MovieResult
import com.example.movies.model.Trailer
import com.example.movies.viewmodel.MoviesViewModel
import javax.inject.Inject

class PopularMoviesDetailFragment : BaseFragment(), TrailersAdapter.TrailerClickInterface {
    lateinit var binding: FragmentPopularMoviesDetailBinding
    lateinit var tabLayoutBinding: FragmentMainBinding
    lateinit var popularMovies: PopularMoviesDetailFragmentArgs
    @Inject
    lateinit var moviesViewModel: MoviesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentPopularMoviesDetailBinding>(
            inflater, R.layout.fragment_popular_movies_detail, container,
            false
        )

        binding.root.setBackgroundColor(Color.WHITE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularMovies = arguments?.let { PopularMoviesDetailFragmentArgs.fromBundle(it) }!!
        moviesViewModel = moviesComponent.getMoviesViewModel()

        Glide.with(this).load(
            "https://image.tmdb.org/t/p/w500/" +
                    popularMovies?.movies?.backdropPath
        )
            .into(binding.popularMoviesBackdropDetailsImageView)
        binding.popularmoviesTitleDetailsTextview.text = popularMovies?.movies?.title
        getTrailers()
    }

    private fun getTrailers() {
        moviesViewModel.getTrailers(popularMovies)?.observe(viewLifecycleOwner, Observer { movies ->
            movies?.results?.let { generateMoviesTrailerList(it) }
        })

    }

    private fun generateMoviesTrailerList(trailersList: List<Trailer>) {
        binding.trailersRecyclerView.setHasFixedSize(true)
        val trailersAdapter = TrailersAdapter(this)
        trailersAdapter.moviesTrailers = trailersList
        binding.trailersRecyclerView.adapter = trailersAdapter
        binding.trailersRecyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL, false
        )
    }

    override fun onMovieTrailerClick(trailers: Trailer) {
       val openYoutubeIntent = Intent(Intent.ACTION_VIEW)
        openYoutubeIntent.data = Uri.parse(YOUTUBE_TRAILER_BASE_URL + trailers.key)
        startActivity(openYoutubeIntent)
    }

}