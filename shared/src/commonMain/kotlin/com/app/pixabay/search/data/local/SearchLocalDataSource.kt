package com.app.pixabay.search.data.local

import com.app.pixabay.search.domain.model.SearchResultDomainModel

interface SearchLocalDataSource {
    suspend fun save(
        query: String,
        data: List<SearchResultDomainModel>,
    )

    fun get(query: String): List<SearchResultDomainModel>
}
