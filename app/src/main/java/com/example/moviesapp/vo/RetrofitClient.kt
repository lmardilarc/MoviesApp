package com.example.moviesapp.vo

import com.example.moviesapp.domain.MovieWebService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val webService by lazy {
        Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(MovieWebService::class.java)
    }

}