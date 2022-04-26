package com.example.movies.network

import androidx.annotation.Nullable
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import javax.inject.Singleton


@Module
class MoviesRetrofitClientInstance {

    companion object retrofitInstance {

        private val retrofit: Retrofit? = null
        private val BASE_URL = "https://api.themoviedb.org/"
        private val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        @Provides
        @Singleton
        fun provideGsonConverterFactory(): GsonConverterFactory? {
            return GsonConverterFactory.create()
        }

        @Provides
        @Singleton
        fun provideInterceptor(): OkHttpClient.Builder {
            val logger: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
            val okHTTP: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(logger)
            val interceptor = Interceptor { chain ->
                val API_KEY = "e39dd47477f4bd2ccf8277df82b9f616&language=en-US&page=1"
                val url = chain.request().url.newBuilder().addQueryParameter("apiKey", API_KEY).build()
                val request = chain.request()
                        .newBuilder()
                        .url(url)
                        .build()
                chain.proceed(request)
            }
            return okHTTP
        }

        @Provides
        @Singleton
        fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory? {
            return RxJava2CallAdapterFactory.create()
        }

        @Provides
        @Singleton
        fun provideMoshiConverterFactory(): MoshiConverterFactory? {
            return MoshiConverterFactory.create(moshi)
        }


        fun getRetrofitInstance(): Retrofit? {

//                if (retrofit == null) {
//                    retrofit = Retrofit.Builder()
//                            .baseUrl(BASE_URL)
//                            .addConverterFactory(GsonConverterFactory.create())
//                            .addConverterFactory(MoshiConverterFactory.create(moshi))
//                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                            .client(okHTTP.build())
//                            .build()
//                }
            return retrofit
        }

        @Provides
        @Singleton
        fun provideRetrofitInstance(client: OkHttpClient.Builder, gsonConverterFactory: GsonConverterFactory?,
                                    adapterFactory: RxJava2CallAdapterFactory?,
                                    moshiConverterFactory: MoshiConverterFactory?): Retrofit? {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addConverterFactory(moshiConverterFactory)
                    .addCallAdapterFactory(adapterFactory)
                    .client(client.build())
                    .build()

        }
    }
    }


