package com.app.pixabay.search.data

import com.app.pixabay.dummy.Dummy
import com.app.pixabay.search.data.local.SearchLocalDataSource
import com.app.pixabay.search.data.mapper.RemoteSearchResultMapper
import com.app.pixabay.search.data.remote.SearchRemoteDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchRepositoryImplTest {
    companion object {
        val query: String = "sample query"
    }

    lateinit var sut: SearchRepositoryImpl

    @RelaxedMockK
    private lateinit var remoteDataSource: SearchRemoteDataSource

    @RelaxedMockK
    private lateinit var searchResultMapper: RemoteSearchResultMapper

    @RelaxedMockK
    private lateinit var localDataSource: SearchLocalDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        sut =
            SearchRepositoryImpl(
                remoteDataSource,
                localDataSource,
                searchResultMapper,
            )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun givenASuccessfulMapping() {
        every { searchResultMapper(Dummy.searchResultDataModel) } returns Dummy.searchResultDomainModel
    }

    @Test
    fun `given empty query for search, must return Empty`(): Unit =
        runTest {
            coEvery { remoteDataSource.searchWith("") } returns Result.success(Dummy.emptySearchResultDataModel)

            val result = sut.search("")

            assert(result.isSuccess)
            assert(result.getOrNull()?.isEmpty() == true)
        }

    @Test
    fun `given a query for search, must return a result`(): Unit =
        runTest {
            coEvery { remoteDataSource.searchWith(query) } returns Result.success(Dummy.searchResultDataModel)
            coEvery { localDataSource.get(query) } returns Dummy.searchResultDomainModel
            givenASuccessfulMapping()

            val result = sut.search(query)

            assert(result.isSuccess)
            Assert.assertTrue(result.getOrNull()?.isNotEmpty() ?: false)
        }

    @Test
    fun `given a query for search,the query and search result must save in database`(): Unit =
        runTest {
            coEvery { remoteDataSource.searchWith(query) } returns Result.success(Dummy.searchResultDataModel)
            coEvery { localDataSource.get(query) } returns Dummy.searchResultDomainModel
            givenASuccessfulMapping()

            val result = sut.search(query)

            assert(result.isSuccess)
            coVerify { localDataSource.save(query, Dummy.searchResultDomainModel) }
        }

    @Test
    fun `given a query for search,the result must return from database`(): Unit =
        runTest {
            coEvery { remoteDataSource.searchWith(query) } returns Result.success(Dummy.searchResultDataModel)
            coEvery { localDataSource.get(query) } returns Dummy.searchResultDomainModel
            givenASuccessfulMapping()

            val result = sut.search(query)

            assert(result.isSuccess)
            coVerify { localDataSource.get(query) }
            assert(localDataSource.get(query).isNotEmpty())
        }
}
