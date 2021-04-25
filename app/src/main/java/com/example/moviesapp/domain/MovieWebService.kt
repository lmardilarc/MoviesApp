package com.example.moviesapp.domain

import androidx.room.Update
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.model.MovieVideosList
import com.example.moviesapp.data.model.PopularMoviesList
import com.example.moviesapp.vo.Const
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieWebService {
    @GET("popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String , @Query("language") lenguage: String = Const.lenguage, @Query("page") page: Int): PopularMoviesList

    @GET("{movieId}")
    suspend fun getMovieDetail(@Path("movieId") movieId: Int, @Query("api_key") apiKey: String , @Query("language") lenguage: String = Const.lenguage): Movie

    @GET("{movieId}/videos")
    suspend fun getMovieVideos(@Path("movieId") movieId: Int, @Query("api_key") apiKey: String , @Query("language") lenguage: String = Const.lenguage): MovieVideosList

}