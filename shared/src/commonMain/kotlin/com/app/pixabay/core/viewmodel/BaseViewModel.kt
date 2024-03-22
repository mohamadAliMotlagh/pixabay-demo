package com.app.pixabay.core.viewmodel

import kotlinx.coroutines.CoroutineScope

expect abstract class BaseViewModel() {
    val coroutineScope: CoroutineScope

    fun dispose()

    protected open fun onCleared()
}
