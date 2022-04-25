package com.example.movies.base

import androidx.fragment.app.Fragment
import com.example.movies.di.DaggerMoviesComponent
import com.example.movies.di.MoviesComponent

abstract class BaseFragment : Fragment() {

 protected  val moviesComponent: MoviesComponent
  get() = DaggerMoviesComponent.create()

}