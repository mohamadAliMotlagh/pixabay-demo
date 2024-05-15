package com.app.pixabay.search.domain

import com.app.pixabay.search.domain.model.SearchResultDomainModel
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun search(query: String): Flow<Result<List<SearchResultDomainModel>>>

    suspend fun findSearchResultById(id: String): SearchResultDomainModel
}
