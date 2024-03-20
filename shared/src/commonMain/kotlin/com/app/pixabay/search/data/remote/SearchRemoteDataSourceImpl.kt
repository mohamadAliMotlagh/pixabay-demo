package com.app.pixabay.search.data.remote

import com.app.pixabay.search.data.network.SearchApi
import com.app.pixabay.search.data.model.SearchResultDataModel

class SearchRemoteDataSourceImpl(private val api: SearchApi) : SearchRemoteDataSource {
    override suspend fun searchWith(query: String): Result<SearchResultDataModel> =
        api.search(query)
}