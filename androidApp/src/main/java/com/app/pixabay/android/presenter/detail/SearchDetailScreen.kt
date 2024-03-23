package com.app.pixabay.android.presenter.detail

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Keyboard
import androidx.compose.material.icons.outlined.ModeComment
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Tag
import androidx.compose.material.icons.rounded.Keyboard
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.app.pixabay.android.presenter.search.SearchResultScreen
import com.app.pixabay.android.presenter.search.SearchViewModel
import com.app.pixabay.android.shimmerEffect
import com.app.pixabay.search.domain.model.SearchResultDomainModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

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


@Composable
fun SearchDetailVertical(info: SearchResultDomainModel) {
    Column(Modifier.fillMaxSize()) {

        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(info.largeImage,)
                .build(),
            contentDescription = info.tags,
            contentScale = ContentScale.FillWidth,
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(info.ratio, true)
                        .shimmerEffect(
                            MaterialTheme.colorScheme.background,
                            RoundedCornerShape(4.dp)
                        )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(info.ratio, true)
        )

        Spacer(Modifier.height(8.dp))

        Column(Modifier.fillMaxSize()) {
            InfoChip(
                info.name,
                Icons.Outlined.PersonOutline
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
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
            }

            InfoChip(
                info.tags,
                Icons.Outlined.Tag
            )
        }
    }

}

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
//        AsyncImage(
//            model = info.largeImage,
//            contentDescription = info.tags,
//            contentScale = ContentScale.FillHeight,
//            modifier =
//            Modifier
//                .fillMaxHeight(),
//        )
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