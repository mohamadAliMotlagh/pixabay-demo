package com.app.pixabay.dummy

import com.app.pixabay.search.data.model.SearchResultDataModel
import com.app.pixabay.search.domain.model.SearchResultDomainModel
import kotlinx.serialization.json.Json

object Dummy {
    val emptySearchResultDataModel = SearchResultDataModel(hits = listOf(), 0, 0)
    val searchResultDataModel: SearchResultDataModel
        get() {
            val json: String = """         
                {
  "total": 7778,
  "totalHits": 500,
  "hits": [
    {
      "id": 436498,
      "pageURL": "https://pixabay.com/photos/old-books-book-old-library-436498/",
      "type": "photo",
      "tags": "old books, book, old",
      "previewURL": "https://cdn.pixabay.com/photo/2014/09/05/18/32/old-books-436498_150.jpg",
      "previewWidth": 150,
      "previewHeight": 99,
      "webformatURL": "https://pixabay.com/get/g4c5bb85617bdf0b52226000ae03e7dc4c1438cf419c2f0e0ddd1f97dfd79dfc1e18aa05d120814bae7d57ecaf1f36023_640.jpg",
      "webformatWidth": 640,
      "webformatHeight": 426,
      "largeImageURL": "https://pixabay.com/get/g3fc4fa292c7c73822de6140fe1d95772891618bf1b2526cd3d672392c79b65f4578ed86005dca4d1bd8bb6449215c5d319af74a406b090f4dd7fd0fffe6c7cf6_1280.jpg",
      "imageWidth": 3000,
      "imageHeight": 2000,
      "imageSize": 1616370,
      "views": 922966,
      "downloads": 486652,
      "collections": 2444,
      "likes": 2278,
      "comments": 363,
      "user_id": 143740,
      "user": "jarmoluk",
      "userImageURL": "https://cdn.pixabay.com/user/2019/09/18/07-14-26-24_250x250.jpg"
    }]
   }
            """.trimIndent()
            return Json.decodeFromString<SearchResultDataModel>(json)
        }

    val searchResultDomainModel = (0..1).map {
        val text = it.toString()
        SearchResultDomainModel(text, text, text, text, it, it, text, text)
    }
}