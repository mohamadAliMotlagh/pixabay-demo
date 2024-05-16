package com.app.pixabay.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.app.pixabay.android.navigation.AppNavigation
import com.app.pixabay.android.navigator.Navigator
import org.koin.android.ext.android.inject

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