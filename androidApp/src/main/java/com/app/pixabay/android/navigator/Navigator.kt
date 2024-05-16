package com.app.pixabay.android.navigator

import android.os.Bundle
import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.Flow

interface Navigator {
    fun navigateUp(): Boolean

    fun popBackStack()

    fun navigate(
        route: String,
        builder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true },
    ): Boolean

    val destinations: Flow<NavigatorEvent>

    fun popBackStackWithParams(bundle: Bundle)
}
