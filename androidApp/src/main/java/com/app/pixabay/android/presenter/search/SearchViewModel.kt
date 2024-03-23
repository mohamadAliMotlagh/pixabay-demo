package com.app.pixabay.android.presenter.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pixabay.android.presenter.detail.SearchDetailDestination
import com.app.pixabay.core.navigator.Navigator
import com.app.pixabay.search.domain.SearchRepository
import com.app.pixabay.search.domain.model.SearchResultDomainModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: SearchRepository,
    private val navigator: Navigator
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _resultFlow = MutableStateFlow(listOf<SearchResultDomainModel>())
    val resultFlow = _resultFlow.asStateFlow()

    lateinit var selectedItem: SearchResultDomainModel

    init {
        onSearchQueried("fruits")
        makeRequest()
    }

    fun onSearchQueried(query: String) {
        _searchQuery.value = query
    }

    private fun makeRequest() {
        viewModelScope.launch {
            _searchQuery.debounce(500).collectLatest {
                repository.search(it).onSuccess {
                    _resultFlow.value = it
                }
            }
        }
    }

    fun onItemClicked(item: SearchResultDomainModel) {
        selectedItem = item
    }

    fun navigateToSearchDetail() {
        navigator.navigate(SearchDetailDestination.route())
    }
}
