package com.example.moviesapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.entity.MovieEntity
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.model.asMovieEntity
import com.example.moviesapp.domain.MoviesRepo
import com.example.moviesapp.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val moviesRepo: MoviesRepo) : ViewModel() {
    //TODO implement pagination
    var popularPage = 1
    var movie: List<Movie>? = null

    val fetchMoviesList = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(moviesRepo.getMoviesList(popularPage))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }


    fun fetchMovieDetail(movieId: Int) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {

            emit(moviesRepo.getMovieDetail(movieId))


        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun fetchMovieDetailDB(movieId: Int) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(moviesRepo.getMovieDetailDB(movieId))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun fetchMovieVideoList(movieId: Int) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(moviesRepo.getMovieVideos(movieId))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun insertMovieDB(movie: Movie) {
        viewModelScope.launch {
            moviesRepo.insertMovieDB(movie.asMovieEntity())
        }
    }

    fun updateMovieDB(movie: Movie) {
        viewModelScope.launch {
            moviesRepo.updateMovieDB(movie.asMovieEntity())
        }
    }
}