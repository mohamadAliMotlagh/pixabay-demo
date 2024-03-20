package com.app.pixabay.search.data

import com.app.pixabay.search.data.mapper.RemoteSearchResultMapper
import com.app.pixabay.search.data.remote.SearchRemoteDataSource
import com.app.pixabay.search.domain.SearchRepository
import com.app.pixabay.search.domain.model.SearchResultDomainModel

class SearchRepositoryImpl(
    private val remote: SearchRemoteDataSource,
    private val remoteSearchResultMapper: RemoteSearchResultMapper,
) : SearchRepository {
    override suspend fun search(query: String): Result<List<SearchResultDomainModel>> {
        if (query.isEmpty()) {
            return Result.success(listOf())
        }
        return remote.searchWith(query).map { remoteSearchResultMapper(it) }
    }
}
