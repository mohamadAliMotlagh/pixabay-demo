package com.app.pixabay.android.presenter.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pixabay.android.R
import com.app.pixabay.android.presenter.detail.SearchDetailDestination
import com.app.pixabay.android.stringprovider.StringProvider
import com.app.pixabay.android.navigator.Navigator
import com.app.pixabay.search.domain.SearchError
import com.app.pixabay.search.domain.SearchUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchUseCase,
    private val navigator: Navigator,
    private val stringProvider: StringProvider,
) : ViewModel() {

    private val _searchQueryFlow =
        MutableStateFlow(stringProvider.getString(R.string.initial_query))
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
                searchUseCase(it).collect {
                    it.onSuccess {
                        launch {
                            _resultFlow.value = SearchResultState.Success(it)
                        }

                    }.onFailure {

                        _resultFlow.value = when (it) {
                            is SearchError.General -> {
                                SearchResultState.Error(stringProvider.getString(R.string.general_server_error))
                            }

                            is SearchError.EmptyResult -> {
                                SearchResultState.Error(stringProvider.getString(R.string.empty_result))
                            }

                            is SearchError.Network -> {
                                SearchResultState.Error(stringProvider.getString(R.string.no_internet))
                            }

                            else -> {
                                SearchResultState.Error(stringProvider.getString(R.string.unknown_error))
                            }
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
