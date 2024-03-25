package com.app.pixabay.android.presenter.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pixabay.android.presenter.detail.SearchDetailDestination
import com.app.pixabay.core.navigator.Navigator
import com.app.pixabay.search.domain.SearchError
import com.app.pixabay.search.domain.SearchRepository
import com.app.pixabay.search.domain.model.SearchResultDomainModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: SearchRepository,
    private val navigator: Navigator
) : ViewModel() {
     companion object{
         const val INITIAL_QUERY = "fruits"
     }

    private val _searchQueryFlow = MutableStateFlow(INITIAL_QUERY)
    val searchQueryFlow = _searchQueryFlow.asStateFlow()

    private val _resultFlow = MutableStateFlow<SearchResultState>(SearchResultState.Loading)
    val resultFlow = _resultFlow.asStateFlow()

    init {
        onSearchQueried(_searchQueryFlow.value)
        makeRequest()
    }

    fun onSearchQueried(query: String) {
        _searchQueryFlow.value = query
    }

    @OptIn(FlowPreview::class)
    private fun makeRequest() {
        viewModelScope.launch {
            _searchQueryFlow.debounce(500).collectLatest {
                _resultFlow.value = SearchResultState.Loading
                repository.search(it)
                    .onSuccess {
                        _resultFlow.value = SearchResultState.Success(it)
                    }.onFailure {

                        _resultFlow.value = when (it) {
                            is SearchError.General -> {
                                SearchResultState.Error("There is an error related to the server.")
                            }

                            is SearchError.EmptyResult -> {
                                SearchResultState.Error("Theres are not any results.")
                            }

                            is SearchError.Network -> {
                                SearchResultState.Error("Your internet connection has issue.")
                            }

                            else -> {
                                SearchResultState.Error("unknown error.")
                            }
                        }


                    }
            }
        }
    }

    fun navigateToSearchDetail(id: String) {
        navigator.navigate(SearchDetailDestination.createDetailPath(id))
    }
}
