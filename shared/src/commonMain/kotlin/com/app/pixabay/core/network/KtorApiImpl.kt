package com.app.pixabay.core.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.api.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.content.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class KtorApiImpl : KtorApi {
    private val baseURl = "https://pixabay.com/"

    override val client = HttpClient {

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 120_000
            connectTimeoutMillis = 120_000
            socketTimeoutMillis = 120_000
        }
    }

    override fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            takeFrom(baseURl)
            encodedPath = path
        }
    }

    override fun HttpRequestBuilder.json() {
        contentType(ContentType.Application.Json)
    }
}