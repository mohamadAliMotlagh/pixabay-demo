package com.app.pixabay.search.domain

import com.app.pixabay.search.domain.model.SearchResultDomainModel
import kotlinx.coroutines.flow.Flow

fun interface SearchUseCase : suspend (String) -> Flow<Result<List<SearchResultDomainModel>>>

suspend fun searchUseCase(
    searchRepository: SearchRepository,
    query: String,
): Flow<Result<List<SearchResultDomainModel>>> {
    return searchRepository.search(query)
}
