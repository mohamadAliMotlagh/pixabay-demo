package com.app.pixabay.search.domain

import com.app.pixabay.search.domain.model.SearchResultDomainModel

fun interface FindResultByIdUseCase : suspend (String) -> SearchResultDomainModel

suspend fun findResultById(
    searchRepository: SearchRepository,
    id: String,
): SearchResultDomainModel {
    return searchRepository.findSearchResultById(id)
}
