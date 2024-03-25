package com.app.pixabay.search.domain

import com.app.pixabay.search.domain.model.SearchResultDomainModel


fun interface SearchUseCase : suspend (String) -> Result<List<SearchResultDomainModel>>

suspend fun searchUseCase(
    searchRepository: SearchRepository,
    query: String
): Result<List<SearchResultDomainModel>> {
   return searchRepository.search(query)
}