package com.example.movies.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.movies.di.DaggerMoviesComponent
import com.example.movies.di.MoviesComponent
import com.example.movies.model.PopularMovies

abstract class BaseFragment : Fragment() {

 val mutableLiveData: MutableLiveData<PopularMovies?> = MutableLiveData<PopularMovies?>()

 protected  val moviesComponent: MoviesComponent
  get() = DaggerMoviesComponent.create()

}