package com.app.pixabay.android.presenter.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.app.pixabay.android.presenter.search.SearchHeader
import com.app.pixabay.android.presenter.search.SearchResultScreen
import com.app.pixabay.search.domain.model.SearchResultDomainModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchUI(
    searchQuery: () -> String,
    onQueryChange: (String) -> Unit,
    list: () -> List<SearchResultDomainModel>,
    onItemClick: (SearchResultDomainModel) -> Unit
) {

    Scaffold { paddingValues ->
        SearchHeader(
            searchQuery = searchQuery,
            paddingValues = { paddingValues },
            onQueryChange
        ) {
            SearchResultScreen(
                list,
                onItemClick
            )
        }
    }
}

@Composable
fun NonInlineColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement, horizontalAlignment = horizontalAlignment, content
    )
}