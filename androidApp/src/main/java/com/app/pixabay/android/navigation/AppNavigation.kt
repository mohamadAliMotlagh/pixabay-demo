package com.app.pixabay.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.pixabay.android.presenter.detail.SearchDetailDestination
import com.app.pixabay.android.presenter.detail.ui.SearchDetailScreen
import com.app.pixabay.android.presenter.search.SearchDestination
import com.app.pixabay.android.presenter.search.ui.SearchScreen
import com.app.pixabay.android.navigator.Navigator
import com.app.pixabay.android.navigator.NavigatorEvent


@Composable
fun AppNavigation(appNavigator: Navigator) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = SearchDestination.route()
    ) {
        composable(
            route = SearchDestination.route(),
            SearchDetailDestination.arguments
        ) {
            SearchScreen()
        }

        composable(
            route = SearchDetailDestination.route(),
            arguments = SearchDetailDestination.arguments
        ) {
            SearchDetailScreen()
        }
    }

    LaunchedEffect(navController) {
        appNavigator.destinations.collect {
            when (val event = it) {
                is NavigatorEvent.NavigateUp -> {
                    navController.navigateUp()
                }

                is NavigatorEvent.Directions -> navController.navigate(
                    event.destination,
                    event.builder
                )

                NavigatorEvent.PopBackStack -> {
                    navController.popBackStack()
                }

                is NavigatorEvent.PopBackStackWithData -> {
                    event.bundle.keySet().forEach { key ->
                        navController
                            .previousBackStackEntry
                            ?.savedStateHandle?.set(
                                key,
                                event.bundle.getString(key)
                            )
                    }

                    navController.popBackStack()
                }
            }
        }
    }

}