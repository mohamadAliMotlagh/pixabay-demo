package com.app.pixabay.android.presenter.search

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.pixabay.search.domain.model.SearchResultDomainModel

@Composable
fun SearchResultItemScreen(
    item: SearchResultDomainModel,
    onItemClicked: (SearchResultDomainModel) -> Unit
) {

    Box(
        modifier =
        Modifier
            .aspectRatio(item.ratio, false)
            .height(IntrinsicSize.Min)
            .clickable {
                onItemClicked(item)
            },
    ) {
        AsyncImage(
            model = item.thumbnail,
            contentDescription = item.tags,
            contentScale = ContentScale.FillWidth,
            modifier =
            Modifier
                .fillMaxWidth(),
        )
        Spacer(Modifier.height(6.dp))
//        Text(
//            modifier = Modifier,
//            overflow = TextOverflow.Ellipsis,
//            text = item.tags,
//            color = MaterialTheme.colorScheme.secondaryContainer,
//        )
    }
}