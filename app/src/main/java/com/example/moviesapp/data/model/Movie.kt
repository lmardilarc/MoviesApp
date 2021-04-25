package com.example.moviesapp.data.model

import android.os.Parcelable
import com.example.moviesapp.data.entity.MovieEntity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Movie(
        @SerializedName("id")
        val id: Int = -1,
        @SerializedName("poster_path")
        val posterPath: String = "",
        @SerializedName("title")
        val title: String = "",
        @SerializedName("vote_average")
        val voteAverage: String = "",
        @SerializedName("overview")
        val movieOverview: String = "",
        @SerializedName("release_date")
        val releaseDate: String = "",
        @SerializedName("video")
        val video: Boolean = false,
        @SerializedName("budget")
        val budget: Double = 0.0,
        var favorite: Boolean = false

) : Parcelable

fun Movie.asMovieEntity(): MovieEntity =
        MovieEntity(this.id, this.posterPath, this.title, this.voteAverage, this.movieOverview, this.releaseDate, this.video, budget, this.favorite)


