package com.app.pixabay.search.data.mapper

import com.app.pixabay.core.util.Mapper
import com.app.pixabay.search.data.model.SearchResultDataModel
import com.app.pixabay.search.domain.model.SearchResultDomainModel

typealias RemoteSearchResultMapper = Mapper<SearchResultDataModel, List<SearchResultDomainModel>>

fun searchResultDataModelToDomainModel(dataModel: SearchResultDataModel): List<SearchResultDomainModel> {
    return dataModel.hits?.map {
        SearchResultDomainModel(
            largeImage = it.largeImageURL ?: "",
            thumbnail = it.previewURL ?: "",
            id = it.id ?: 0,
            tags = it.tags ?: "",
            downloadsCount = it.downloads ?: 0,
            commentsCount = it.comments ?: 0,
            name = it.user ?: "",
            username = it.userId.toString(),
        )
    } ?: listOf()
}
