package com.app.pixabay.core.network

import com.app.pixabay.core.util.resultOf
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

suspend inline fun <reified T> Result<HttpResponse>.receiveResult(): Result<T> {
    return this.mapCatching {
        if (it.status.isSuccess()) {
            val data = it.body<T>()
            data
        } else {
            val data = it.body<ErrorModel>()
            throw Exception(data.detail ?: "")
        }
    }
}

suspend fun HttpClient.get(block: HttpRequestBuilder.() -> Unit): Result<HttpResponse> =
    resultOf { get(HttpRequestBuilder().apply(block)) }


suspend fun HttpClient.post(block: HttpRequestBuilder.() -> Unit): Result<HttpResponse> =
    resultOf { post(HttpRequestBuilder().apply(block)) }