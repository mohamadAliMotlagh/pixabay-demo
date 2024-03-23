package com.app.pixabay.android.presenter.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.app.pixabay.android.shimmerEffect
import com.app.pixabay.search.domain.model.SearchResultDomainModel

@Composable
fun SearchResultItemScreen(
    item: SearchResultDomainModel,
    onItemClicked: (SearchResultDomainModel) -> Unit
) {

    Box(
        modifier =
        Modifier
            .fillMaxWidth()
            .aspectRatio(item.ratio, true)
            .clickable {
                onItemClicked(item)
            },
    ) {

        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.thumbnail)
                .build(),
            contentDescription = item.tags,
            contentScale = ContentScale.FillWidth,
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .shimmerEffect(
                            MaterialTheme.colorScheme.background,
                            RoundedCornerShape(4.dp)
                        )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        )
//        Text(
//            modifier = Modifier,
//            overflow = TextOverflow.Ellipsis,
//            text = item.tags,
//            color = MaterialTheme.colorScheme.secondaryContainer,
//        )
    }
}