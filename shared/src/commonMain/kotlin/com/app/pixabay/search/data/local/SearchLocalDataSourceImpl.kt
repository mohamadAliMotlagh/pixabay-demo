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
                    it.id,
                    it.tags,
                    it.downloadsCount.toLong(),
                    it.commentsCount.toLong(),
                    it.likesCount.toLong(),
                    it.name,
                    it.username,
                    it.ratio.toDouble(),
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
                    it.result_id,
                    it.tags,
                    it.downloads_count.toInt(),
                    it.comments_count.toInt(),
                    it.likes_count.toInt(),
                    it.name,
                    it.username,
                    it.ratio.toFloat(),
                )
            }
        }

    override suspend fun findSearchResultById(id: String): SearchResultDomainModel =
        withContext(ioDispatcher) {
            val result = queries.findItemByResultId(id).executeAsOne()
            SearchResultDomainModel(
                result.large_image,
                result.thumbnail,
                result.result_id,
                result.tags,
                result.downloads_count.toInt(),
                result.comments_count.toInt(),
                result.likes_count.toInt(),
                result.name,
                result.username,
                result.ratio.toFloat(),
            )
        }
}
