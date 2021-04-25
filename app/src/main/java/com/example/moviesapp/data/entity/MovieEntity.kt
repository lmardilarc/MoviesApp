package com.example.moviesapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesapp.data.model.Movie

@Entity
data class MovieEntity(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: Int,
        @ColumnInfo(name = "poster_path")
        val posterPath: String,
        @ColumnInfo(name = "title")
        val title: String,
        @ColumnInfo(name = "vote_average")
        val voteAverage: String,
        @ColumnInfo(name = "overview")
        val movieOverview: String,
        @ColumnInfo(name = "release_date")
        val releaseDate: String,
        @ColumnInfo(name = "video")
        val video: Boolean = false,
        @ColumnInfo(name = "budget")
        val budget: Double,
        @ColumnInfo(name = "favorite")
        val favorite: Boolean
) {
    fun asMovie(): Movie {
        return Movie(this.id, this.posterPath, this.title, this.voteAverage, this.movieOverview, this.releaseDate, this.video, budget, this.favorite)
    }
}

