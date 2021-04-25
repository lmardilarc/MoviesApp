package com.example.moviesapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.vo.Const

class MoviesListAdapter(private val context: Context, private val moviesList: List<Movie>, private val onMovieClickListener: OnMovieClickListener) : RecyclerView.Adapter<MoviesListAdapter.MoviesListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.movies_list_adapter, parent, false)
        return MoviesListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) {
        val movie: Movie = moviesList[position]
        holder.bindView(movie)
    }

    inner class MoviesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieImage: ImageView = itemView.findViewById(R.id.imgv_movie)
        private val movieTitle: TextView = itemView.findViewById(R.id.txtv_movie_title)
        private val movieAverage: TextView = itemView.findViewById(R.id.txtv_movie_average)

        fun bindView(movie: Movie) {

            Glide.with(context).load("${Const.posterBase}${movie.posterPath}").centerCrop().placeholder(R.drawable.ic_no_video_recording).into(movieImage)
            movieTitle.text = movie.title
            movieAverage.text = movie.voteAverage
            itemView.setOnClickListener { onMovieClickListener.onMovieCliced(movie) }
        }
    }


    interface OnMovieClickListener {

        fun onMovieCliced(movie: Movie)
    }
}