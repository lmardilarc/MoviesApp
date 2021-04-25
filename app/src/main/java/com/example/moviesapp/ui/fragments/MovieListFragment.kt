package com.example.moviesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.data.AppDatabase
import com.example.moviesapp.data.datasource.MovieDataSource
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.domain.MovieRepoImpl
import com.example.moviesapp.ui.adapters.MoviesListAdapter
import com.example.moviesapp.ui.viewModel.MovieViewModel
import com.example.moviesapp.ui.viewModel.MovieVmFactory
import com.example.moviesapp.vo.Resource


class MovieListFragment : Fragment(), MoviesListAdapter.OnMovieClickListener {

    private val movieViewModel by viewModels<MovieViewModel> {
        MovieVmFactory(MovieRepoImpl(MovieDataSource(AppDatabase.getDatabase(requireActivity().applicationContext))))
    }

    private lateinit var rvMoviesList: RecyclerView
    private lateinit var progresBar: ConstraintLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvMoviesList = view.findViewById(R.id.rv_movies_list)
        progresBar = view.findViewById(R.id.progres_bar)
        setupRecyclerView()
        setupMovieListObserver()
    }

    private fun setupRecyclerView() {
        rvMoviesList.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun setupMovieListObserver() {
        movieViewModel.fetchMoviesList.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    progresBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progresBar.visibility = View.INVISIBLE
                    rvMoviesList.adapter = MoviesListAdapter(requireContext(), it.data, this)
                }
                is Resource.Failure -> {
                    progresBar.visibility = View.INVISIBLE
                    Toast.makeText(requireContext(), "An error has ocurred ${it.exception}", Toast.LENGTH_LONG).show()
                }
            }

        })
    }


    private fun setupMovieDetailObserver(movieId: Int) {
        movieViewModel.fetchMovieDetail(movieId).observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    progresBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progresBar.visibility = View.INVISIBLE
                    val bundle = Bundle()
                    bundle.putParcelable(getString(R.string.selectedMovie), it.data)
                    movieViewModel.run {
                        bundle.putParcelable(getString(R.string.selectedMovie), it.data)
                        insertMovieDB(it.data)
                    }
                    findNavController().navigate(R.id.movieDetailFragment, bundle)
                }
                is Resource.Failure -> {
                    progresBar.visibility = View.INVISIBLE
                    Toast.makeText(requireContext(), "An error has ocurred ${it.exception}", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun setupMovieDetailBDObserver(movieId: Int) {
        movieViewModel.fetchMovieDetailBD(movieId).observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    progresBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progresBar.visibility = View.INVISIBLE
                    val bundle = Bundle()
                    if (it.data == null) {
                        setupMovieDetailObserver(movieId)
                    } else {
                        bundle.putParcelable(getString(R.string.selectedMovie), it.data)
                        findNavController().navigate(R.id.movieDetailFragment, bundle)
                    }

                }
                is Resource.Failure -> {
                    progresBar.visibility = View.INVISIBLE
                    Toast.makeText(requireContext(), "An error has ocurred ${it.exception}", Toast.LENGTH_LONG).show()
                }
            }

        })
    }


    override fun onMovieCliced(movie: Movie) {
        // setupMovieDetailObserver(movie.id)
        setupMovieDetailBDObserver(movie.id)
    }

}