package com.app.pixabay.search.data

import com.app.pixabay.search.data.local.SearchLocalDataSource
import com.app.pixabay.search.data.mapper.RemoteSearchResultMapper
import com.app.pixabay.search.data.remote.SearchRemoteDataSource
import com.app.pixabay.search.domain.SearchError
import com.app.pixabay.search.domain.SearchRepository
import com.app.pixabay.search.domain.model.SearchResultDomainModel
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.map

class SearchRepositoryImpl(
    private val remote: SearchRemoteDataSource,
    private val local: SearchLocalDataSource,
    private val remoteSearchResultMapper: RemoteSearchResultMapper,
) : SearchRepository {
    /**
     * Performs a search operation for the given query.
     * The search is first attempted locally, and if not found,
     * it's then attempted remotely.
     * @param query The search query.
     * @return A Result object containing a list of search results.
     */
    override suspend fun search(query: String): Result<List<SearchResultDomainModel>> {
        if (query.isEmpty()) {
            return Result.success(listOf())
        }

        val localResult = loadFromLocal(query)
        if (localResult.isSuccess) {
            return localResult
        }

        return loadFromRemote(query)
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
            Result.failure(exceptionHandler(result.exceptionOrNull()))
        }
    }


    private fun exceptionHandler(result: Throwable?): Throwable {
        val exception = result ?: Exception("Unknown error")
        return when (exception) {
            is IOException -> {
                SearchError.Network
            }

            else -> {
                SearchError.General
            }
        }
    }
}
