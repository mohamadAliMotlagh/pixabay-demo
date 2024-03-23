package com.app.pixabay.android.presenter.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.pixabay.android.presenter.search.SearchResultScreen
import com.app.pixabay.android.presenter.search.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchDetailScreen() {
    Column(Modifier.fillMaxSize()) {
        val viewModel = koinViewModel<SearchViewModel>()

            SearchResultScreen(
                { listOf(viewModel.selectedItem) },
                {  }
            )


    }
}