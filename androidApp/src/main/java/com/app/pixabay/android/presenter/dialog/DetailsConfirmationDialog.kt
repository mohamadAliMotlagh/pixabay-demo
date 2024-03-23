package com.app.pixabay.android.presenter.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DialogScreen(
    title: String,
    description: String,
    positiveButtonText: String,
    negativeButtonText: String,
    onPositiveButtonClick: () -> Unit,
    onNegativeButtonClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {onNegativeButtonClick.invoke() },
        title = {
            Text(text = title)
        },
        text = {
            Text(text = description)
        },
        confirmButton = {
            Button(
                onClick = onPositiveButtonClick
            ) {
                Text(text = positiveButtonText)
            }
        },
        dismissButton = {
            Button(
                onClick = onNegativeButtonClick
            ) {
                Text(text = negativeButtonText)
            }
        }
    )
}