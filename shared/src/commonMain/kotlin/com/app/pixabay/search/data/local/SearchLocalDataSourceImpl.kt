package com.app.pixabay.search.data.local

import com.app.pixabay.database.PixPayBackDatabase
import com.app.pixabay.search.domain.model.SearchResultDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class SearchLocalDataSourceImpl(
    db: PixPayBackDatabase,
    private val ioDispatcher: CoroutineDispatcher,
) : SearchLocalDataSource {
    private val queries = db.pixPayBackDatabaseQueries

    override suspend fun save(
        query: String,
        data: List<SearchResultDomainModel>,
    ) {
        withContext(Dispatchers.IO) {
            queries.insertQueries(query, 0)

            queries.insertQueries(query, 0)
            val queryID = queries.getQueries(query).executeAsOne().id

            data.forEach {
                queries.insertSearchResults(
                    queryID,
                    it.largeImage,
                    it.thumbnail,
                    it.id,
                    it.tags,
                    it.downloadsCount.toLong(),
                    it.commentsCount.toLong(),
                    it.name,
                    it.username,
                )
            }
        }
    }

    override suspend fun get(query: String): List<SearchResultDomainModel> =
        withContext(Dispatchers.IO) {
            queries.localSearch(query).executeAsList().map {
                SearchResultDomainModel(
                    it.large_image,
                    it.thumbnail,
                    it.id.toString(),
                    it.tags,
                    it.downloads_count.toInt(),
                    it.comments_count.toInt(),
                    it.name,
                    it.username,
                )
            }
        }
}
