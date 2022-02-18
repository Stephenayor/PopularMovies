package com.example.movies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movies.model.PopularMovies
import com.example.movies.repository.MoviesRepository

class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    private var popularMoviesRepository: MoviesRepository? = null
    private var mutableLiveData: MutableLiveData<PopularMovies?>? = null

    fun PopularMoviesViewmodel() {
    }

    fun getPopularMovies(): LiveData<PopularMovies?>? {
        popularMoviesRepository = MoviesRepository()
        if (mutableLiveData == null) {
            mutableLiveData = popularMoviesRepository!!.getPopularMovies()
        }
        return mutableLiveData
    }

}