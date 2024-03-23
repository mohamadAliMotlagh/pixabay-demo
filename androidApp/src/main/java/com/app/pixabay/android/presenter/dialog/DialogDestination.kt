package com.app.pixabay.android.presenter.dialog

import androidx.compose.ui.window.DialogProperties
import com.app.pixabay.core.navigator.NavigationDestination

object DialogDestination : NavigationDestination {
    override fun route() = "DetailsConfirmationDialog"

    override val dialogProperties: DialogProperties
        get() = DialogProperties()
}