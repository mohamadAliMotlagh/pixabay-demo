package com.app.pixabay.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.pixabay.android.presenter.search.SearchViewModel
import com.app.pixabay.android.presenter.detail.SearchDetailDestination
import com.app.pixabay.android.presenter.detail.SearchDetailScreen
import com.app.pixabay.android.presenter.dialog.DialogScreen
import com.app.pixabay.android.presenter.search.SearchDestination
import com.app.pixabay.android.presenter.search.SearchUI
import com.app.pixabay.core.navigator.Navigator
import com.app.pixabay.core.navigator.NavigatorEvent
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    private val appNavigator: Navigator by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = SearchDestination.route()
                    ) {
                        composable(route = SearchDestination.route(),SearchDetailDestination.arguments) {
                            SearchScreen()
                        }

                        composable(route = SearchDetailDestination.route(), arguments = SearchDetailDestination.arguments) {
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
            }
        }
    }
}


@Composable
fun SearchScreen() {
    val viewModel = koinViewModel<SearchViewModel>()
    val text = viewModel.searchQuery.collectAsStateWithLifecycle()
    val list = viewModel.resultFlow.collectAsStateWithLifecycle()
    SearchUI(
        searchQuery = { text.value },
        onQueryChange = viewModel::onSearchQueried,
        list = { list.value },
        {
            viewModel.navigateToSearchDetail(it)
        }
    )
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        SearchScreen()
    }
}
