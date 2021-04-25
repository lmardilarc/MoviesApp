package com.example.moviesapp.domain

import androidx.room.*
import com.example.moviesapp.data.entity.MovieEntity
import com.example.moviesapp.data.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieEntity WHERE  id =:detailId")
    suspend fun getMovieDetail(detailId: Int): MovieEntity

    @Query("SELECT * FROM MovieEntity WHERE favorite")
    suspend fun getFavoriteMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieBD(movie: MovieEntity)

    @Update
    suspend fun updateMovie(movieEntity: MovieEntity)
}