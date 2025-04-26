package com.arcee.parkit.presentation.favorites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcee.parkit.common.Resource
import com.arcee.parkit.domain.use_case.list_favorites.ListFavoritesUseCase
import com.arcee.parkit.domain.use_case.remove_from_favorites.RemoveFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val listFavoritesUseCase: ListFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
) : ViewModel() {
    private val _state = mutableStateOf(FavoritesState())
    val state: State<FavoritesState> = _state

    val stateFlow = flow {
        emit(Resource.Loading())
        try {
            val pagingDataFlow = listFavoritesUseCase()
            emit(Resource.Success(pagingDataFlow))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: "Unknown error"))
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Resource.Loading()
    )

    fun removeFromFavorites(id: Long) {
        removeFromFavoritesUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {

                }

                is Resource.Error -> {
                    _state.value =
                        FavoritesState(
                            error = result.message ?: "An unexpected error occurred.",
                            isLoading = false
                        )
                }

                is Resource.Loading -> {
                    _state.value = FavoritesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}