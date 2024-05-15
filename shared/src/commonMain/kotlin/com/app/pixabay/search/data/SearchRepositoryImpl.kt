package com.app.pixabay.search.data

import com.app.pixabay.search.data.local.SearchLocalDataSource
import com.app.pixabay.search.data.mapper.RemoteSearchResultMapper
import com.app.pixabay.search.data.mapper.exceptionMapper
import com.app.pixabay.search.data.remote.SearchRemoteDataSource
import com.app.pixabay.search.domain.SearchError
import com.app.pixabay.search.domain.SearchRepository
import com.app.pixabay.search.domain.model.SearchResultDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class SearchRepositoryImpl(
    private val remote: SearchRemoteDataSource,
    private val local: SearchLocalDataSource,
    private val remoteSearchResultMapper: RemoteSearchResultMapper,
) : SearchRepository {
    override suspend fun search(query: String): Flow<Result<List<SearchResultDomainModel>>> =
        flow {
            if (query.isEmpty()) {
                emit(Result.success(listOf()))
            } else {
                val localResult = loadFromLocal(query)
                if (localResult.isSuccess) {
                    emit(localResult)
                }

                val remoteResult = loadFromRemote(query)
                if (remoteResult.isSuccess) {
                    emit(remoteResult)
                }

                if (remoteResult.isFailure && localResult.isFailure) {
                    emit(remoteResult)
                }
            }
        }

    override suspend fun findSearchResultById(id: String): SearchResultDomainModel {
        return local.findSearchResultById(id)
    }

    private suspend fun loadFromLocal(query: String): Result<List<SearchResultDomainModel>> {
        val result = local.get(query)
        return if (result.isEmpty()) {
            (Result.failure(Throwable("")))
        } else {
            (Result.success(result))
        }
    }

    private suspend fun loadFromRemote(query: String): Result<List<SearchResultDomainModel>> {
        val result = remote.searchWith(query)
        return if (result.isSuccess) {
            val mappedResult = result.map { remoteSearchResultMapper(it) }

            mappedResult.getOrNull()?.let {
                if (it.isEmpty()) {
                    return Result.failure(SearchError.EmptyResult)
                }
                local.save(query, it)
            }
            mappedResult
        } else {
            Result.failure(exceptionMapper(result.exceptionOrNull()))
        }
    }
}
