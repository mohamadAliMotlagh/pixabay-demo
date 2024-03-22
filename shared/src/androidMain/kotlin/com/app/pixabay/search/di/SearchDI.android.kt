package com.app.pixabay.search.di

import com.app.pixabay.database.PixPayBackDatabase
import com.app.pixabay.search.presenter.SearchViewModel
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sqlDriverModule: Module =
    module {
        single<SqlDriver> {
            AndroidSqliteDriver(
                PixPayBackDatabase.Schema,
                get(),
                "PixPayBackDatabase.db",
            )
        }
    }

actual val searchViewModel =
    module {
        viewModel { SearchViewModel(get()) }
    }
