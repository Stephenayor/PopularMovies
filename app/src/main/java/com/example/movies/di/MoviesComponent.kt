package com.example.movies.di

import com.example.movies.repository.MoviesRepository
import com.example.movies.view.PopularMoviesFragment
import com.example.movies.viewmodel.MoviesViewModel
import dagger.Component


@Component
interface MoviesComponent  {

    fun getMoviesViewModel(): MoviesViewModel
//    fun getMoviesRepository(): MoviesRepository
    fun inject(popularMoviesFragment: PopularMoviesFragment)

}