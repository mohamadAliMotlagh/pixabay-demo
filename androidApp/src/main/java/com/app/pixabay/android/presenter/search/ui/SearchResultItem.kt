package com.app.pixabay.android.presenter.search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.app.pixabay.android.components.shimmerEffect
import com.app.pixabay.search.domain.model.SearchResultDomainModel

@Composable
internal fun SearchResultItem(
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(item.ratio, true)
                .drawBehind {
                    val gradientColors = listOf(Color.Black.copy(alpha = 0.7f),Color.Transparent,Color.Transparent, Color.Black.copy(alpha = 0.99f))
                    val gradient = Brush.verticalGradient(
                        startY = 0f,
                        endY = size.height,
                        colors = gradientColors
                    )
                    drawRect(brush = gradient)
                }
        ) {
            Text(
                text = item.tags,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Left
                ),
                modifier = Modifier.padding(8.dp).align(Alignment.BottomStart)
            )

            Text(
                text = item.name,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Left
                ),
                modifier = Modifier.padding(8.dp).align(Alignment.TopStart)
            )
        }
    }
}