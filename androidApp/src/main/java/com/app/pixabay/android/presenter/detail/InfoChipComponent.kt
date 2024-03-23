package com.app.pixabay.android.presenter.detail

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun InfoChip(title: String, vector: ImageVector) {
    AssistChip(
        onClick = {},
        label = { Text(text = title) },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = vector,
                contentDescription = vector.name
            )
        }
    )
}