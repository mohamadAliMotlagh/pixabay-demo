package com.app.pixabay.android.presenter.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pixabay.search.domain.FindResultByIdUseCase
import com.app.pixabay.search.domain.SearchRepository
import com.app.pixabay.search.domain.model.SearchResultDomainModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val findResultByIdUseCase: FindResultByIdUseCase
) : ViewModel() {

    private val _selectedItemFlow = MutableStateFlow<SearchResultDomainModel?>(null)
    val selectedItemFlow = _selectedItemFlow.asStateFlow()


    init {
        val id = savedStateHandle.get<String>(SearchDetailDestination.RESULT_ID) ?: "0"
        viewModelScope.launch {
            _selectedItemFlow.value = findResultByIdUseCase(id)
        }

    }

}