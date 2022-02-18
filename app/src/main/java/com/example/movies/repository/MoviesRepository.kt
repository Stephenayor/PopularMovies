package com.example.movies.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.movies.model.PopularMovies
import com.example.movies.network.MoviesRetrofitClientInstance
import com.example.movies.network.PopularMoviesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRepository {
    fun getPopularMovies(): MutableLiveData<PopularMovies?>? {
        val mutableLiveData: MutableLiveData<PopularMovies?> = MutableLiveData<PopularMovies?>()

        val moviesApiService: PopularMoviesApi = MoviesRetrofitClientInstance.getRetrofitInstance()
                ?.create(PopularMoviesApi::class.java) !!
        val call: Call<PopularMovies> = moviesApiService.getAllPopularMovies()
        call.enqueue(object : Callback<PopularMovies?> {
            override fun onResponse(call: Call<PopularMovies?>, response: Response<PopularMovies?>) {
                mutableLiveData.value = response.body()
            }

            override fun onFailure(call: Call<PopularMovies?>, t: Throwable) {
                t.message
            }
        })

        return mutableLiveData
    }
}