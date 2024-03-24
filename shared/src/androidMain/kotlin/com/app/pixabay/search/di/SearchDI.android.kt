package com.app.pixabay.search.di

import android.content.Context
import com.app.pixabay.core.navigator.Navigator
import com.app.pixabay.core.navigator.NavigatorImpl
import com.app.pixabay.database.PixPayBackDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
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
val navigator = module {
    single<Navigator> { NavigatorImpl() }
}


