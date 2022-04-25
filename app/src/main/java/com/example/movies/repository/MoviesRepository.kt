package com.example.movies.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.movies.model.PopularMovies
import com.example.movies.model.TrailersResult
import com.example.movies.network.MoviesRetrofitClientInstance
import com.example.movies.network.PopularMoviesApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MoviesRepository @Inject constructor() {

    val mutableLiveData: MutableLiveData<PopularMovies?> = MutableLiveData<PopularMovies?>()
    val mutableLiveDataTrailer: MutableLiveData<TrailersResult?> = MutableLiveData<TrailersResult?>()

//    fun getPopularMovies(): MutableLiveData<PopularMovies?>? {
//        val mutableLiveData: MutableLiveData<PopularMovies?> = MutableLiveData<PopularMovies?>()
//
//        val moviesApiService: PopularMoviesApi = MoviesRetrofitClientInstance.getRetrofitInstance()
//                ?.create(PopularMoviesApi::class.java) !!
//        val call: Call<PopularMovies> = moviesApiService.getAllPopularMovies()
//        call.enqueue(object : Callback<PopularMovies?> {
//            override fun onResponse(call: Call<PopularMovies?>, response: Response<PopularMovies?>) {
//                mutableLiveData.value = response.body()
//            }
//
//            override fun onFailure(call: Call<PopularMovies?>, t: Throwable) {
//                t.message
//            }
//        })
//
//        return mutableLiveData
//    }

    fun getTopRatedMovies(): MutableLiveData<PopularMovies?>? {
        val mutableLiveData: MutableLiveData<PopularMovies?> = MutableLiveData<PopularMovies?>()

        val moviesApiService: PopularMoviesApi = MoviesRetrofitClientInstance.getRetrofitInstance()
                ?.create(PopularMoviesApi::class.java) !!
        val call: Call<PopularMovies> = moviesApiService.getTopRatedMovies()
        call.enqueue(object : Callback<PopularMovies?> {
            override fun onResponse(call: Call<PopularMovies?>, response: Response<PopularMovies?>) {
                Log.d("Success", "TOP RATED")
                mutableLiveData.value = response.body()
            }

            override fun onFailure(call: Call<PopularMovies?>, t: Throwable) {
                t.message
            }
        })

        return mutableLiveData
    }

    fun getObservablePopularMovies(): Observable<PopularMovies>? {
      return MoviesRetrofitClientInstance.getRetrofitInstance()?.create(PopularMoviesApi::class.java)
                ?.getAllPopularMovies()
                ?.subscribeOn(Schedulers.io())
              ?.observeOn(AndroidSchedulers.mainThread())

    }

    fun getTrailers(moviesID: Int): MutableLiveData<TrailersResult?> {
        val moviesApiService: PopularMoviesApi = MoviesRetrofitClientInstance.getRetrofitInstance()
                ?.create(PopularMoviesApi::class.java) !!
        val call: Call<TrailersResult> = moviesApiService.getTrailers(moviesID)
        call.enqueue(object : Callback<TrailersResult>{
            override fun onResponse(call: Call<TrailersResult>, response: Response<TrailersResult>) {
                Log.d("Success", "TRAILERS COMING IN")
                mutableLiveDataTrailer.value = response.body()
            }
            override fun onFailure(call: Call<TrailersResult>, t: Throwable) {
            }

        })

       return mutableLiveDataTrailer
    }
}