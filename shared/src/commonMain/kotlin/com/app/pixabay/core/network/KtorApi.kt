package com.app.pixabay.core.network

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder

interface KtorApi {
    val client: HttpClient

    fun HttpRequestBuilder.apiUrl(path: String)

    fun HttpRequestBuilder.json()
}
