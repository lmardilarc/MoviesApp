package com.example.moviesapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.domain.MoviesRepo

class MovieVmFactory(private val movieRepo: MoviesRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MoviesRepo::class.java).newInstance(movieRepo)
    }

}