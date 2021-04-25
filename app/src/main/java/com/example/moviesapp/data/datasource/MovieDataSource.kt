package com.example.moviesapp.data.datasource

import com.example.moviesapp.data.AppDatabase
import com.example.moviesapp.data.entity.MovieEntity
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.vo.Const
import com.example.moviesapp.vo.Resource
import com.example.moviesapp.vo.RetrofitClient

class MovieDataSource(private val appDatabase: AppDatabase) {

    suspend fun getPopularMovies(page: Int): Resource<List<Movie>> {
        return Resource.Success(RetrofitClient.webService.getPopularMovies(Const.apiKey, Const.lenguage, 1).movieList)
    }

    suspend fun getMovieDetail(movieId: Int): Resource.Success<Movie> {
        return Resource.Success(RetrofitClient.webService.getMovieDetail(movieId, Const.apiKey, Const.lenguage))
    }


    suspend fun getMovieDetailBD(movieId: Int): Resource.Success<Movie?> {

        val movie: MovieEntity = appDatabase.movieDao().getMovieDetail(movieId)
        if (movie == null) {
            return Resource.Success(null)
        } else {
            return Resource.Success(appDatabase.movieDao().getMovieDetail(movieId)!!.asMovie())
        }
    }

    suspend fun insertMovieBD(movie: MovieEntity) {
        appDatabase.movieDao().insertMovieBD(movie)
    }

    suspend fun updateMovieBD(movie: MovieEntity) {
        appDatabase.movieDao().updateMovie(movie)
    }
}