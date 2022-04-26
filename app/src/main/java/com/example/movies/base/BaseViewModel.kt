package com.example.movies.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.movies.model.PopularMovies

abstract class BaseViewModel : AndroidViewModel(Application()){

     var mutableLiveData: MutableLiveData<PopularMovies?>? = null

}