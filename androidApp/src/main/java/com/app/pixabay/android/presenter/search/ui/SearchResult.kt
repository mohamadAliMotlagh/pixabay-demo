package com.app.pixabay.android.presenter.search.ui

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.app.pixabay.search.domain.model.SearchResultDomainModel


@Composable
fun SearchResult(
    list: () -> List<SearchResultDomainModel>,
    onItemClicked: (SearchResultDomainModel) -> Unit
) {

    val cellConfiguration = if (LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE) {
        StaggeredGridCells.Adaptive(minSize = 200.dp)
    } else StaggeredGridCells.Adaptive(128.dp)

    LazyVerticalStaggeredGrid(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 8.dp)
        .padding(top = 16.dp),
        columns = cellConfiguration,
        contentPadding = PaddingValues(8.dp),
        verticalItemSpacing = 16.dp,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content = {

            items(list.invoke(), key = { it.id }) {
                SearchResultItem(it,onItemClicked)
            }
        })
}