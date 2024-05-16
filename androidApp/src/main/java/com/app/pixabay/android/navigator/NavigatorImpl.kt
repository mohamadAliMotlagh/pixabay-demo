package com.app.pixabay.android.navigator

import android.os.Bundle
import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * Created by funkymuse on 6/25/21 to long live and prosper !
 */
class NavigatorImpl : Navigator {
    private val navigationEvents = Channel<NavigatorEvent>()
    override val destinations = navigationEvents.receiveAsFlow()

    override fun popBackStackWithParams(bundle: Bundle) {
        navigationEvents.trySend(NavigatorEvent.PopBackStackWithData(bundle))
    }

    override fun navigateUp(): Boolean = navigationEvents.trySend(NavigatorEvent.NavigateUp).isSuccess

    override fun popBackStack() {
        navigationEvents.trySend(NavigatorEvent.PopBackStack)
    }

    override fun navigate(
        route: String,
        builder: NavOptionsBuilder.() -> Unit,
    ): Boolean = navigationEvents.trySend(NavigatorEvent.Directions(route, builder)).isSuccess
}
