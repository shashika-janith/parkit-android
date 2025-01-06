package com.arcee.parkit.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.arcee.parkit.domain.model.Provider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    pager: Pager<Int, Provider>
) : ViewModel() {
    val providerPagingFlow = pager
        .flow
        .cachedIn(viewModelScope)
}