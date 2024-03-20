package com.app.pixabay.search.data.mapper

import com.app.pixabay.core.util.Mapper
import com.app.pixabay.search.data.model.SearchResultDataModel
import com.app.pixabay.search.domain.model.SearchResultDomainModel


typealias RemoteSearchResultMapper = Mapper<SearchResultDataModel, List<SearchResultDomainModel>>

fun searchResultDataModelToDomainModel(dataModel: SearchResultDataModel): List<SearchResultDomainModel> {
    return listOf()
}
