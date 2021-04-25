package com.example.moviesapp.domain

import com.example.moviesapp.data.entity.MovieEntity
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.model.MovieVideo
import com.example.moviesapp.vo.Resource

interface MoviesRepo {

    suspend fun getMoviesList(page:Int): Resource<List<Movie>>
    suspend fun getMovieDetail(movieId: Int): Resource<Movie>
    suspend fun getMovieDetailDB(movieId: Int): Resource<Movie?>
    suspend fun insertMovieDB(movie: MovieEntity)
    suspend fun updateMovieDB(movie: MovieEntity)
    suspend fun getMovieVideos(movieId: Int): Resource<List<MovieVideo>>

}