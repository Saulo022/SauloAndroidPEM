package com.example.sauloandroidpem.movies.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sauloandroidpem.movies.domain.*
import com.example.sauloandroidpem.movies.ui.model.FavMovieModel
import com.example.sauloandroidpem.movies.ui.model.MoviesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavMoviesViewModel @Inject constructor(
    getFavMoviesUseCase: GetFavMoviesUseCase,
    private val updateFavMoviesUseCase: UpdateFavMoviesUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    val uiState: StateFlow<MoviesUiState> = getFavMoviesUseCase().map(MoviesUiState::Success)
        .catch { MoviesUiState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MoviesUiState.Loading)

    fun onItemRemove(favMovieModel: FavMovieModel) {
        viewModelScope.launch {
            deleteTaskUseCase(favMovieModel)
        }
    }

    fun onItemUpdated(favMovieModel: FavMovieModel) {
        viewModelScope.launch {
            updateFavMoviesUseCase(favMovieModel.copy(selected = !favMovieModel.selected))
        }
    }
}