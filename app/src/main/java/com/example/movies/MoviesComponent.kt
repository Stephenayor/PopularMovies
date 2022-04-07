package com.example.movies

import android.app.Application
import com.example.movies.view.PopularMoviesFragment
import com.example.movies.viewmodel.MoviesViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component (modules = [MoviesViewModelModule::class])
interface MoviesComponent  {

    fun getMoviesViewModel(): MoviesViewModel
    fun inject(popularMoviesFragment: PopularMoviesFragment)

//    @Component.Builder
//    interface Builder {
//
//        fun build(): MoviesComponent
//
//        @BindsInstance
//        fun application(app: Application): Builder
//    }
}