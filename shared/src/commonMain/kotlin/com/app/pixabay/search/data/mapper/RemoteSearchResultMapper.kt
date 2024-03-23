package com.app.pixabay.search.data.mapper

import com.app.pixabay.core.util.Mapper
import com.app.pixabay.search.data.model.SearchResultDataModel
import com.app.pixabay.search.domain.model.SearchResultDomainModel

typealias RemoteSearchResultMapper = Mapper<SearchResultDataModel, List<SearchResultDomainModel>>

fun searchResultDataModelToDomainModel(dataModel: SearchResultDataModel): List<SearchResultDomainModel> {
    return dataModel.hits?.map {
        SearchResultDomainModel(
            largeImage = it.largeImageURL ?: "",
            thumbnail = it.webformatURL ?: "",
            id = it.id.toString(),
            tags = it.tags ?: "",
            downloadsCount = it.downloads ?: 0,
            commentsCount = it.comments ?: 0,
            likesCount = it.likes ?: 0,
            name = it.user ?: "",
            username = it.userId.toString(),
            ratio =
            try {
                ((it.webformatWidth ?: 0).toFloat() /
                        (it.webformatHeight ?: 0).toFloat())
            } catch (e: Exception) {
                (16f / 9f)
            },
        )
    } ?: listOf()
}
