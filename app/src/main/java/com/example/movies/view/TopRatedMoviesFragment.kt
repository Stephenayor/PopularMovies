package com.example.movies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.R
import com.example.movies.adapter.PopularMoviesAdapter
import com.example.movies.adapter.TopRatedMoviesAdapter
import com.example.movies.databinding.FragmentPopularMoviesBinding
import com.example.movies.databinding.FragmentTopRatedMoviesBinding
import com.example.movies.model.MovieResult
import com.example.movies.viewmodel.MoviesViewModel
import com.example.movies.viewmodel.MoviesViewModelFactory
import java.util.ArrayList

class TopRatedMoviesFragment : Fragment() {
    private lateinit var moviesViewModel: MoviesViewModel
    lateinit var binding: FragmentTopRatedMoviesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentTopRatedMoviesBinding>(inflater,
                R.layout.fragment_top_rated_movies, container, false)

        setUpViewModel()
        moviesViewModel.getTopRatedMovies()?.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                generatePopularMoviesList(list.results)
            }
        })
        return binding.root
    }

    private fun generatePopularMoviesList(movieResult: List<MovieResult>) {
        val topRatedMoviesAdapter = TopRatedMoviesAdapter()
        topRatedMoviesAdapter.movies = movieResult
        binding.topRatedMoviesRecyclerView.adapter = topRatedMoviesAdapter
        binding.topRatedMoviesRecyclerView.addItemDecoration(
                DividerItemDecoration(
                        context,
                        GridLayoutManager.HORIZONTAL
                ))

    }

    private fun setUpViewModel() {
        val application = requireNotNull(this.activity).application
        val viewModelFactory = MoviesViewModelFactory(application)
        moviesViewModel =
                ViewModelProvider(
                        this, viewModelFactory
                ).get(MoviesViewModel::class.java)
    }
}