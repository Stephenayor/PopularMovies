package com.example.movies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movies.model.PopularMovies
import com.example.movies.repository.MoviesRepository
import io.reactivex.Observable

class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    private var moviesRepository: MoviesRepository? = null
    private var mutableLiveData: MutableLiveData<PopularMovies?>? = null

    fun PopularMoviesViewmodel() {
    }

    fun getPopularMovies(): LiveData<PopularMovies?>? {
//        moviesRepository = MoviesRepository()
//        if (mutableLiveData == null) {
//            mutableLiveData = moviesRepository!!.getPopularMovies()
//        }
        return mutableLiveData
    }

    fun getTopRatedMovies(): LiveData<PopularMovies?>? {
        moviesRepository = MoviesRepository()
        if (mutableLiveData == null) {
            mutableLiveData = moviesRepository!!.getTopRatedMovies()
        }
        return mutableLiveData
    }

    fun getObservablePopularMovies(): Observable<PopularMovies>? {
        moviesRepository = MoviesRepository()
       return  moviesRepository?.getObservablePopularMovies()
    }

}