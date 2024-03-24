package com.app.pixabay.android.presenter.detail.ui

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.pixabay.android.presenter.detail.SearchDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Preview
@Composable
fun SearchDetailScreen() {
    val viewModel = koinViewModel<SearchDetailViewModel>()
    val resultItem by viewModel.selectedItemFlow.collectAsStateWithLifecycle()
    resultItem?.let {
        val configuration = LocalConfiguration.current
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            SearchDetailHorizontal(info = it)
        } else {
            SearchDetailVertical(info = it)
        }
    }
}