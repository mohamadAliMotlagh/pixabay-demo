package com.app.pixabay.search.data

import com.app.pixabay.dummy.Dummy
import com.app.pixabay.search.data.mapper.RemoteSearchResultMapper
import com.app.pixabay.search.data.remote.SearchRemoteDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SearchRepositoryImplTest {
    lateinit var sut: SearchRepositoryImpl

    @RelaxedMockK
    private lateinit var remoteDataSource: SearchRemoteDataSource

    @RelaxedMockK
    private lateinit var searchResultMapper: RemoteSearchResultMapper

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        sut =
            SearchRepositoryImpl(
                remoteDataSource,
                searchResultMapper,
            )
    }

    @After
    fun tearDown() {
    }

    private fun givenASuccessfulMapping() {
        every { searchResultMapper(Dummy.searchResultDataModel) } returns Dummy.searchResultDomainModel
    }

    @Test
    fun `given empty query for search, must return Empty`(): Unit =
        runBlocking {
            coEvery { remoteDataSource.searchWith("") } returns Result.success(Dummy.emptySearchResultDataModel)

            val result = sut.search("")

            assert(result.isSuccess)
            assert(result.getOrNull()?.isEmpty() == true)
        }

    @Test
    fun `given a query for search, must return a result`(): Unit =
        runBlocking {
            coEvery { remoteDataSource.searchWith("data") } returns Result.success(Dummy.searchResultDataModel)
            givenASuccessfulMapping()

            val result = sut.search("data")

            assert(result.isSuccess)
            Assert.assertTrue((result.getOrNull()?.size ?: 0) > 1)
        }
}
