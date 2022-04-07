package com.example.movies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movies.DaggerMoviesComponent
import com.example.movies.model.PopularMovies
import com.example.movies.repository.MoviesRepository
import dagger.Provides
import io.reactivex.Observable
import javax.inject.Inject

class MoviesViewModel @Inject constructor() : AndroidViewModel(Application()) {

    @Inject
    lateinit var moviesRepository: MoviesRepository
    var moviesComponent = DaggerMoviesComponent.create()!!
    private var mutableLiveData: MutableLiveData<PopularMovies?>? = null

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
        moviesRepository = moviesComponent.getMoviesRepository()
        if (mutableLiveData == null) {
            mutableLiveData = moviesRepository!!.getTopRatedMovies()
        }
        return mutableLiveData
    }

    fun getObservablePopularMovies(): Observable<PopularMovies>? {
        moviesRepository = moviesComponent.getMoviesRepository()
       return  moviesRepository?.getObservablePopularMovies()
    }

}