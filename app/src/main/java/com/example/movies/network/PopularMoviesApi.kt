package com.example.movies.network

import com.example.movies.model.PopularMovies
import retrofit2.Call
import retrofit2.http.GET

interface PopularMoviesApi {

//    var popularMoviesKey = "e39dd47477f4bd2ccf8277df82b9f616&language=en-US&page=1"
companion object api {}

    @GET("3/movie/popular?api_key=e39dd47477f4bd2ccf8277df82b9f616&language=en-US&page=1")
    fun getAllPopularMovies():
            Call<PopularMovies>

}