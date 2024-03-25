package com.app.pixabay.android.presenter.search.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.app.pixabay.android.presenter.dialog.DialogScreen
import com.app.pixabay.android.presenter.search.SearchResultState
import com.app.pixabay.search.domain.model.SearchResultDomainModel

@Composable
fun SearchUI(
    searchQuery: () -> String,
    onQueryChange: (String) -> Unit,
    resultState: () -> SearchResultState,
    onItemClick: (String) -> Unit
) {
    val showConfirmDialog = remember {
        mutableStateOf<String?>(null)
    }
    Scaffold { paddingValues ->
        SearchableContainer(
            searchQuery = searchQuery,
            paddingValues = { paddingValues },
            onQueryChange
        ) {
            when (val result = resultState.invoke()) {
                is SearchResultState.Error -> Box(Modifier.fillMaxSize()) {
                    Text(text = result.message, modifier = Modifier.align(Alignment.Center))
                }

                SearchResultState.Loading -> Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is SearchResultState.Success -> {
                    SearchResult({ result.data }) {
                        showConfirmDialog.value = it.id
                    }
                }
            }

        }

        showConfirmDialog.value?.let {
            DialogScreen(
                title = "Confirmation",
                description = "Are you sure you want to see the details?",
                positiveButtonText = "YES",
                negativeButtonText = "CANCEL",
                onPositiveButtonClick = {
                    onItemClick(it)
                    showConfirmDialog.value = null
                },
                onNegativeButtonClick = {
                    showConfirmDialog.value = null
                }
            )
        }
    }
}