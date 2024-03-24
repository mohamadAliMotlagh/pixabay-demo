package com.app.pixabay.android.presenter.detail.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ModeComment
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Tag
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.app.pixabay.android.components.InfoChip
import com.app.pixabay.android.components.shimmerEffect
import com.app.pixabay.search.domain.model.SearchResultDomainModel

@Composable
fun SearchDetailHorizontal(info: SearchResultDomainModel) {
    Row(Modifier.fillMaxSize()) {

        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(info.largeImage)
                .build(),
            contentDescription = info.tags,
            contentScale = ContentScale.FillHeight,
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(info.ratio, true)
                        .shimmerEffect(
                            MaterialTheme.colorScheme.background,
                            RoundedCornerShape(4.dp)
                        )
                )
            },
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(info.ratio, true)
        )
        Spacer(Modifier.width(8.dp))

        Column(Modifier.fillMaxHeight()) {
            InfoChip(
                info.name,
                Icons.Outlined.PersonOutline
            )

            Spacer(modifier = Modifier.weight(1f))
            InfoChip(
                info.likesCount.toString(),
                Icons.Outlined.FavoriteBorder
            )
            Spacer(modifier = Modifier.weight(1f))

            InfoChip(
                info.commentsCount.toString(),
                Icons.Outlined.ModeComment
            )

            Spacer(modifier = Modifier.weight(1f))
            InfoChip(
                info.downloadsCount.toString(),
                Icons.Outlined.Download
            )
            Spacer(modifier = Modifier.weight(1f))


            InfoChip(
                info.tags,
                Icons.Outlined.Tag
            )
        }
    }
}