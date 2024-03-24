package com.app.pixabay.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.pixabay.android.navigation.AppNavigation
import com.app.pixabay.android.presenter.search.SearchViewModel
import com.app.pixabay.android.presenter.detail.SearchDetailDestination
import com.app.pixabay.android.presenter.detail.ui.SearchDetailScreen
import com.app.pixabay.android.presenter.search.SearchDestination
import com.app.pixabay.android.presenter.search.ui.SearchScreen
import com.app.pixabay.android.presenter.search.ui.SearchUI
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
                    AppNavigation(appNavigator)
                }
            }
        }
    }
}