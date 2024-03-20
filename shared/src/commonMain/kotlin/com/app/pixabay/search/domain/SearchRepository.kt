package com.app.pixabay.search.domain

import com.app.pixabay.search.domain.model.SearchResultDomainModel

interface SearchRepository {
    suspend fun search(query: String): Result<List<SearchResultDomainModel>>
}