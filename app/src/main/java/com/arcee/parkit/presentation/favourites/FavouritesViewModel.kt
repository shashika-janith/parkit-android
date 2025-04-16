package com.arcee.parkit.presentation.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcee.parkit.common.Resource
import com.arcee.parkit.domain.use_case.list_favorites.ListFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    listFavoritesUseCase: ListFavoritesUseCase
) : ViewModel() {

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

}