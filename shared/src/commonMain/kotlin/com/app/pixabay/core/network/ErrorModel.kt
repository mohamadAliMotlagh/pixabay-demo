package com.app.pixabay.core.network


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorModel(
    @SerialName("detail")
    val detail: String? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("path")
    val path: String? = null,
    @SerialName("status")
    val status: Int? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("type")
    val type: String? = null
)