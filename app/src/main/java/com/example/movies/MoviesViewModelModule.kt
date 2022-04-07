package com.example.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movies.viewmodel.MoviesViewModel
import com.example.movies.viewmodel.MoviesViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class MoviesViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: MoviesViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    abstract fun bindContactSourcesViewModel(viewModel: MoviesViewModel): ViewModel
}