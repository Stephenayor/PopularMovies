package com.example.movies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movies.base.BaseViewModel
import com.example.movies.di.DaggerMoviesComponent
import com.example.movies.model.PopularMovies
import com.example.movies.model.TrailersResult
import com.example.movies.repository.MoviesRepository
import com.example.movies.view.PopularMoviesDetailFragmentArgs
import io.reactivex.Observable
import javax.inject.Inject

class MoviesViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var moviesRepository: MoviesRepository
    var moviesComponent = DaggerMoviesComponent.create()!!
    private var mutableLiveData: MutableLiveData<PopularMovies?>? = null
    private var mutableLiveDataTrailersResult: MutableLiveData<TrailersResult?>? = null

    fun MoviesViewModel() {
    }

    fun getPopularMovies(): LiveData<PopularMovies?>? {
//        moviesRepository = MoviesRepository()
//        if (mutableLiveData == null) {
//            mutableLiveData = moviesRepository!!.getPopularMovies()
//        }
        return mutableLiveData
    }

    fun getTopRatedMovies(): LiveData<PopularMovies?>? {
        if (mutableLiveData == null) {
            mutableLiveData = moviesRepository!!.getTopRatedMovies()
        }
        return mutableLiveData
    }

    fun getObservablePopularMovies(): Observable<PopularMovies>? {
        return moviesRepository?.getObservablePopularMovies()
    }

    fun getTrailers(popularMovies: PopularMoviesDetailFragmentArgs): MutableLiveData<TrailersResult?>? {
//        return popularMovies.movies.id?.let { moviesRepository.getTrailers(it) }
        mutableLiveDataTrailersResult = popularMovies.movies.id?.let {
            moviesRepository.getTrailers(it)
        }
        return mutableLiveDataTrailersResult
    }


}