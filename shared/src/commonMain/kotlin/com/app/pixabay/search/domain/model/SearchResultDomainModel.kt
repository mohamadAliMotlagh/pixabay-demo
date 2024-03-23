package com.app.pixabay.search.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class SearchResultDomainModel(
    val largeImage: String,
    val thumbnail: String,
    val id: Int,
    val tags: String,
    val downloadsCount: Int,
    val commentsCount: Int,
    val name: String,
    val username: String,
    val ratio: Float,
)
