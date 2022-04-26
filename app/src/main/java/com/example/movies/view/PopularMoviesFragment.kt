package com.example.movies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.R
import com.example.movies.adapter.PopularMoviesAdapter
import com.example.movies.base.BaseFragment
import com.example.movies.databinding.FragmentPopularMoviesBinding
import com.example.movies.model.MovieResult
import com.example.movies.model.PopularMovies
import com.example.movies.viewmodel.MoviesViewModel
import com.example.movies.viewmodel.MoviesViewModelFactory
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class PopularMoviesFragment : BaseFragment(), PopularMoviesAdapter.MovieClickInterface {
    lateinit var binding: FragmentPopularMoviesBinding
    @Inject
    lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentPopularMoviesBinding>(
                inflater, R.layout.fragment_popular_movies, container,
                false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesViewModel = moviesComponent.getMoviesViewModel()
        getPopularMovies()
    }

    private fun generatePopularMoviesList(movieResult: List<MovieResult>) {
        val popularMoviesAdapter = PopularMoviesAdapter(this)
        popularMoviesAdapter.movies = movieResult
        binding.popularMoviesRecyclerView.adapter = popularMoviesAdapter
        val layoutManager = GridLayoutManager(activity, 3)
        binding.popularMoviesRecyclerView.layoutManager = layoutManager
        binding.popularMoviesRecyclerView.addItemDecoration(
                DividerItemDecoration(
                        context,
                        GridLayoutManager.HORIZONTAL)
        )
    }

    override fun onMovieClick(movies: MovieResult) {
        findNavController().navigate(
                PopularMoviesFragmentDirections
                        .actionPopularMoviesFragmentToPopularMoviesDetailFragment(movies))
    }

    private fun getPopularMovies() {
        moviesViewModel.getObservablePopularMovies()?.subscribe(
                object : Observer<PopularMovies> {
                    override fun onSubscribe(disposable: Disposable) {
                        Toast.makeText(context, "SUBSCRIBED", Toast.LENGTH_LONG).show()
                    }

                    override fun onNext(movies: PopularMovies) {
                        generatePopularMoviesList(movies.results)
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                        Toast.makeText(context, "COMPLETE", Toast.LENGTH_LONG).show()
                    }

                })
    }
}










