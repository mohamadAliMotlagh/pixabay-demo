package com.app.pixabay.android.presenter.search.dummy


import com.app.pixabay.search.data.model.SearchResultDataModel
import com.app.pixabay.search.domain.model.SearchResultDomainModel

object Dummy {
    val emptySearchResultDataModel = SearchResultDataModel(hits = listOf(), 0, 0)


    val searchResultDomainModel =
        (0..50).map {
            val text = it.toString()
            SearchResultDomainModel(
                largeImage = "https://pixabay.com/get/g4e4132e3845154e123cadf99598ee454789b792aca0e246d1d57d46078b9e90900219cf0d833815728cf56f0d545c9a3_640.jpg",
                thumbnail = "https://pixabay.com/get/g4e4132e3845154e123cadf99598ee454789b792aca0e246d1d57d46078b9e90900219cf0d833815728cf56f0d545c9a3_640.jpg",
                id = it.toString(),
                tags = "tag$it",
                downloadsCount = it,
                commentsCount = it,
                likesCount = it,
                name = "name$text",
                username = "username$text",
                ratio = 150 / 84f
            )
        }
}
