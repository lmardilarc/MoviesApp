package com.example.moviesapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class PopularMoviesList(
        @SerializedName("results")
        val movieList: List<Movie>,
        @SerializedName("page")
        val page: Int,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("total_results")
        val totalResults: Int
)

