package com.app.pixabay.search.di

import com.app.pixabay.core.di.dispatchersModule
import com.app.pixabay.core.network.ktorProvider
import com.app.pixabay.database.PixPayBackDatabase
import com.app.pixabay.search.data.SearchRepositoryImpl
import com.app.pixabay.search.domain.SearchRepository
import com.app.pixabay.search.domain.model.SearchResultDomainModel
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sqlDriverModule: Module =
    module {
        single<SqlDriver> {
            NativeSqliteDriver(PixPayBackDatabase.Schema, "PixPayBackDatabase.db")
        }
    }


val helper = module {
    single { InjectHelper() }
}
fun initKoin() {
    startKoin {
        modules(
            ktorProvider,
            dispatchersModule,
        )
        modules(searchModules())
    }
}



class InjectHelper:KoinComponent  {
    private val searchRepository: SearchRepository by inject()
    private val scope = CoroutineScope(Dispatchers.Main)
    fun search(query: String, success: (List<SearchResultDomainModel>) -> Unit) {
        scope.launch {
            success(searchRepository.search(query).getOrDefault(listOf()))
        }
    }
}


