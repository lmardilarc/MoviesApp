package com.example.moviesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.R.color
import com.example.moviesapp.data.AppDatabase
import com.example.moviesapp.data.datasource.MovieDataSource
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.domain.MovieRepoImpl
import com.example.moviesapp.ui.viewModel.MovieViewModel
import com.example.moviesapp.ui.viewModel.MovieVmFactory
import com.example.moviesapp.vo.Const
import kotlinx.android.synthetic.main.fragment_movie_detail.*


class MovieDetailFragment : Fragment() {

    private val movieViewModel by viewModels<MovieViewModel> {
        MovieVmFactory(MovieRepoImpl(MovieDataSource(AppDatabase.getDatabase(requireActivity().applicationContext))))
    }

    private lateinit var movie: Movie
    private lateinit var txtTitle: TextView
    private lateinit var txtReleaseDate: TextView
    private lateinit var txtTrailer: TextView
    private lateinit var txtOverview: TextView
    private lateinit var txtBudget: TextView
    private lateinit var txtFavorite: TextView

    private lateinit var imgPoster: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            movie = it.getParcelable(getString(R.string.selectedMovie))!!
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }


    private fun setupView(view: View) {
        imgPoster = view.findViewById(R.id.img_poster)
        txtTitle = view.findViewById(R.id.txt_title)
        txtTrailer = view.findViewById(R.id.txt_trailer)
        txtReleaseDate = view.findViewById(R.id.txt_release_date)
        txtOverview = view.findViewById(R.id.txt_overview)
        txtBudget = view.findViewById(R.id.txt_budget)
        txtFavorite = view.findViewById(R.id.txt_favorite)
        txtFavorite.setOnClickListener {
            movie.favorite = (!movie.favorite)
            movieViewModel.updateMovieDB(movie)
            checkFavorite()
        }

        Glide.with(requireContext()).load("${Const.posterBase}${movie.posterPath}").centerCrop().placeholder(R.drawable.ic_no_video_recording).into(imgPoster)
        txtTitle.text = movie.title
        txtReleaseDate.text = movie.releaseDate
        txtBudget.text = movie.budget.toString()
        txtOverview.text = movie.movieOverview

        checkFavorite()

        if (movie.video) {
            txtTrailer.text = getString(R.string.see_trailer)
        } else {
            txtTrailer.text = getString(R.string.no_video)
        }
    }


    fun checkFavorite() {
        if (movie.favorite) {
            txtFavorite.setTextColor(requireContext().getColor(color.design_default_color_primary))
            imageView.setColorFilter(ContextCompat.getColor(requireContext(), color.design_default_color_primary), android.graphics.PorterDuff.Mode.SRC_IN)
        } else {
            txtFavorite.setTextColor(requireContext().getColor(color.darker_gray))
            imageView.setColorFilter(ContextCompat.getColor(requireContext(), color.darker_gray), android.graphics.PorterDuff.Mode.SRC_IN)
        }
    }

}