package com.ilmint.sharedui.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilmint.sharedui.data.Movie
import com.ilmint.sharedui.data.MovieServices
import com.ilmint.sharedui.data.RemoteMovie
import com.ilmint.sharedui.data.movies
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieServices: MovieServices
) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(isLoading = true)
            //delay(timeMillis = 1000)
            state = UiState(
                isLoading = false,
                movies = movieServices.fetchPopularMovies().results.map { it.toDomainMovie() }
            )

        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )
}

private fun RemoteMovie.toDomainMovie() = Movie(
    id = id,
    title = title,
    poster = "https://image.tmdb.org/t/p/w500$posterPath"
)
