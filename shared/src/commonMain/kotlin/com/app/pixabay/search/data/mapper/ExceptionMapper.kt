package com.app.pixabay.search.data.mapper

import com.app.pixabay.search.domain.SearchError
import io.ktor.utils.io.errors.IOException

fun exceptionMapper(result: Throwable?): Throwable {
    val exception = result ?: Exception("Unknown error")
    return when (exception) {
        is IOException -> {
            SearchError.Network
        }

        else -> {
            SearchError.General
        }
    }
}