package com.example.movies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.DaggerMoviesComponent
import com.example.movies.R
import com.example.movies.adapter.PopularMoviesAdapter
import com.example.movies.databinding.FragmentPopularMoviesBinding
import com.example.movies.model.MovieResult
import com.example.movies.model.PopularMovies
import com.example.movies.viewmodel.MoviesViewModel
import com.example.movies.viewmodel.MoviesViewModelFactory
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class PopularMoviesFragment : Fragment(), PopularMoviesAdapter.MovieClickInterface {
    lateinit var binding: FragmentPopularMoviesBinding
    @Inject
    lateinit var moviesViewModel: MoviesViewModel
    lateinit var viewModelFactory: MoviesViewModelFactory
    lateinit var navController: NavController

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

        navController = this.findNavController()!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moviesComponent = DaggerMoviesComponent.create()
        moviesViewModel = moviesComponent.getMoviesViewModel()
        getPopularMovies()
    }

    private fun setUpViewModel() {
        val application = requireNotNull(this.activity).application
        val viewModelFactory = MoviesViewModelFactory(application)
        moviesViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(MoviesViewModel::class.java)
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
                GridLayoutManager.HORIZONTAL
            )
        )
    }

    override fun onMovieClick(movies: MovieResult) {
        val popularMoviesDetailFragment: Fragment = PopularMoviesDetailFragment()
        val bundle = Bundle()
        bundle.putParcelable("movies", movies)
        popularMoviesDetailFragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction().remove(PopularMoviesFragment())
            .replace(R.id.popular_movies_fragment, popularMoviesDetailFragment)
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commit()

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










