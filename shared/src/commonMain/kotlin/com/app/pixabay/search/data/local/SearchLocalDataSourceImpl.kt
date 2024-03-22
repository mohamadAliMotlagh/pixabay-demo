package com.app.pixabay.search.data.local

import com.app.pixabay.database.PixPayBackDatabase
import com.app.pixabay.search.domain.model.SearchResultDomainModel
import kotlinx.coroutines.CoroutineDispatcher
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
        withContext(ioDispatcher) {
            queries.insertQueries(query, 0)

            val queryID = queries.getQueries(query).executeAsOneOrNull()?.id ?: 0

            data.forEach {
                queries.insertSearchResults(
                    queryID,
                    it.largeImage,
                    it.thumbnail,
                    it.id.toString(),
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
        withContext(ioDispatcher) {
            queries.localSearch(query).executeAsList().map {
                SearchResultDomainModel(
                    it.large_image,
                    it.thumbnail,
                    it.id.toInt(),
                    it.tags,
                    it.downloads_count.toInt(),
                    it.comments_count.toInt(),
                    it.name,
                    it.username,
                )
            }
        }
}
