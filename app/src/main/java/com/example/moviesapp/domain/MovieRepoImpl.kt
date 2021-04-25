package com.example.moviesapp.domain

import com.example.moviesapp.data.datasource.MovieDataSource
import com.example.moviesapp.data.entity.MovieEntity
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.vo.Resource

class MovieRepoImpl(private val movieDataSource: MovieDataSource) : MoviesRepo {

    override suspend fun getMoviesList(): Resource<List<Movie>> {
        return movieDataSource.getPopularMovies(1)
    }

    override suspend fun getMovieDetail(movieId: Int): Resource<Movie> {
        return movieDataSource.getMovieDetail(movieId)
    }

    override suspend fun getMovieDetailDB(movieId: Int): Resource<Movie?> {
        return movieDataSource.getMovieDetailBD(movieId)
    }

    override suspend fun insertMovieDB(movie: MovieEntity) {
        movieDataSource.insertMovieBD(movie)
    }

    override suspend fun updateMovieDB(movie: MovieEntity) {
        movieDataSource.updateMovieBD(movie)
    }


}
