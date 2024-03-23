package com.app.pixabay.search.di

import com.app.pixabay.core.di.ioDispatcher
import com.app.pixabay.database.PixPayBackDatabase
import com.app.pixabay.search.data.SearchRepositoryImpl
import com.app.pixabay.search.data.local.SearchLocalDataSource
import com.app.pixabay.search.data.local.SearchLocalDataSourceImpl
import com.app.pixabay.search.data.mapper.searchResultDataModelToDomainModel
import com.app.pixabay.search.data.network.SearchApi
import com.app.pixabay.search.data.remote.SearchRemoteDataSource
import com.app.pixabay.search.data.remote.SearchRemoteDataSourceImpl
import com.app.pixabay.search.domain.SearchRepository
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

expect val sqlDriverModule: Module

private val pixPayBackDatabaseDriver =
    module {
        single { PixPayBackDatabase(get()) }
    }

private val searchRepository =
    module {
        factory<SearchRepository> {
            SearchRepositoryImpl(get(), get()) {
                searchResultDataModelToDomainModel(
                    it,
                )
            }
        }
    }

private val searchLocalDataSource =
    module {
        single<SearchLocalDataSource> { SearchLocalDataSourceImpl(get(), get(named(ioDispatcher))) }
    }

private val searchApi =
    module {
        single { SearchApi(get()) }
    }

private val searchRemoteDataSource =
    module {
        single<SearchRemoteDataSource> { SearchRemoteDataSourceImpl(get()) }
    }


internal fun searchModules(): List<Module> =
    listOf(
        sqlDriverModule,
        pixPayBackDatabaseDriver,
        searchRepository,
        searchLocalDataSource,
        searchApi,
        searchRemoteDataSource,
    )
