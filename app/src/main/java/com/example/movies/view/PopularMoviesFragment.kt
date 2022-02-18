package com.example.movies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.R
import com.example.movies.adapter.PopularMoviesAdapter
import com.example.movies.databinding.FragmentPopularMoviesBinding
import com.example.movies.model.MovieResult
import com.example.movies.model.PopularMovies
import com.example.movies.network.MoviesRetrofitClientInstance
import com.example.movies.network.PopularMoviesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PopularMoviesFragment : Fragment() {
    lateinit var binding: FragmentPopularMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentPopularMoviesBinding>(
                inflater, R.layout.fragment_popular_movies, container,
                false)

        val moviesApiService: PopularMoviesApi = MoviesRetrofitClientInstance.getRetrofitInstance()
            ?.create(PopularMoviesApi::class.java) !!
        val call: Call<PopularMovies> = moviesApiService.getAllPopularMovies()
        call.enqueue(object : Callback<PopularMovies?> {
            override fun onResponse(call: Call<PopularMovies?>, response: Response<PopularMovies?>) {
                Toast.makeText(activity, "SUCCESSFUL RESPONSE", Toast.LENGTH_LONG).show()
                response.body()?.results?.let { generatePopularMoviesList(it) }
            }

            override fun onFailure(call: Call<PopularMovies?>, t: Throwable) {
                t.message
            }
        })
        return binding.root
    }

     fun generatePopularMoviesList(movieResult: List<MovieResult>){
        val popularMoviesAdapter = PopularMoviesAdapter()
         popularMoviesAdapter.movies = movieResult
         binding.popularMoviesRecyclerView.adapter = popularMoviesAdapter
         val layoutManager = GridLayoutManager(activity, 3)
         binding.popularMoviesRecyclerView.layoutManager = layoutManager
         binding.popularMoviesRecyclerView.addItemDecoration(
                 DividerItemDecoration(
                         context,
                         GridLayoutManager.HORIZONTAL
                 ))
    }
}