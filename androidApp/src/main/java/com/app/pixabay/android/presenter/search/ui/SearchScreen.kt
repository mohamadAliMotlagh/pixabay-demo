package com.app.pixabay.android.presenter.search.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.pixabay.android.presenter.search.SearchViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun SearchScreen() {
    val viewModel = koinViewModel<SearchViewModel>()
    val searchQuery = viewModel.searchQueryFlow.collectAsStateWithLifecycle()
    val result = viewModel.resultFlow.collectAsStateWithLifecycle()
    SearchUI(
        searchQuery = { searchQuery.value },
        onQueryChange = viewModel::onSearchQueried,
        resultState = { result.value },
        onItemClick = viewModel::navigateToSearchDetail
    )
}