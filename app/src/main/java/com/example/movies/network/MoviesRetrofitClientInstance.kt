package com.example.movies.network

import android.accessibilityservice.GestureDescription
import android.os.Build
import androidx.annotation.RequiresApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.util.*


class MoviesRetrofitClientInstance {

    companion object retrofitInstance {
        private var retrofit: Retrofit? = null
        private val BASE_URL = "https://api.themoviedb.org/"

        private val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        private val logger: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
        private val okHTTP: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(logger)
        private val interceptor = Interceptor { chain ->
            val API_KEY = "e39dd47477f4bd2ccf8277df82b9f616&language=en-US&page=1"
            val url = chain.request().url.newBuilder().addQueryParameter("apiKey", API_KEY).build()
            val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
            chain.proceed(request)
        }

            fun getRetrofitInstance(): Retrofit? {

                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addConverterFactory(MoshiConverterFactory.create(moshi))
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(okHTTP.build())
                            .build()
                }
                return retrofit
            }

//            override fun intercept(chain: Interceptor.Chain): Response {
//                var request: Request = chain.request()
//                request = request.newBuilder()
//                        .addHeader("api_key", "e39dd47477f4bd2ccf8277df82b9f616&language=en-US&page=1")
//                        .build()
//                val response: Response = chain.proceed(request)
//                return response
//            }
        }
    }


