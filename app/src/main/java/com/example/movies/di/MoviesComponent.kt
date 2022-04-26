package com.example.movies.di

import com.example.movies.network.MoviesRetrofitClientInstance
import com.example.movies.network.PopularMoviesApi
import com.example.movies.repository.MoviesRepository
import com.example.movies.view.PopularMoviesFragment
import com.example.movies.viewmodel.MoviesViewModel
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [MoviesRetrofitClientInstance::class])
interface MoviesComponent {

    fun getMoviesViewModel(): MoviesViewModel

    fun inject(popularMoviesFragment: PopularMoviesFragment)

    fun getRetrofitInstance(): Retrofit?
}