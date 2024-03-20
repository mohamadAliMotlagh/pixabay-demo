package com.app.pixabay.search.data.network

import com.app.pixabay.core.network.KtorApi
import com.app.pixabay.core.network.get
import com.app.pixabay.core.network.receiveResult
import com.app.pixabay.search.data.model.SearchResultDataModel


class SearchApi(private val api: KtorApi) : KtorApi by api {
    suspend fun search(query: String): Result<SearchResultDataModel> {
        return client.get {
            apiUrl("api/?key=42914219-b240a7afd80a14436e336b9aa&q=${query}&image_type=photo&pretty=true")
            json()
        }.receiveResult<SearchResultDataModel>()
    }
}