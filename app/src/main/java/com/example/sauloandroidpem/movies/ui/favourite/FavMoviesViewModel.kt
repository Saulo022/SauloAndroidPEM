package com.example.sauloandroidpem.movies.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sauloandroidpem.movies.domain.AddFavMoviesUseCase
import com.example.sauloandroidpem.movies.domain.GetFavMoviesUseCase
import com.example.sauloandroidpem.movies.domain.MoviesUseCase
import com.example.sauloandroidpem.movies.ui.model.MoviesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class FavMoviesViewModel @Inject constructor(getFavMoviesUseCase: GetFavMoviesUseCase): ViewModel() {

    val uiState: StateFlow<MoviesUiState> = getFavMoviesUseCase().map(MoviesUiState::Success)
        .catch { MoviesUiState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MoviesUiState.Loading)

}