package com.app.pixabay.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import org.koin.core.component.KoinComponent

actual abstract class BaseViewModel : ViewModel(), KoinComponent {
    actual val coroutineScope = viewModelScope

    actual fun dispose() {
        coroutineScope.cancel()
        onCleared()
    }

    actual override fun onCleared() {
        super.onCleared()
    }
}
