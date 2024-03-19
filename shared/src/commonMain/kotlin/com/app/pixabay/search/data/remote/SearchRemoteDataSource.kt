package com.app.pixabay.search.data.remote

import com.app.pixabay.search.data.model.SearchResultDataModel

interface SearchRemoteDataSource {
    suspend fun searchWith(query: String): Result<SearchResultDataModel>
}