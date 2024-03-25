package com.app.pixabay.android.presenter.search

import com.app.pixabay.search.domain.model.SearchResultDomainModel

sealed class SearchResultState {
    data object Loading : SearchResultState()
    data class Success(val data: List<SearchResultDomainModel>) : SearchResultState()
    data class Error(val message: String) : SearchResultState()
}