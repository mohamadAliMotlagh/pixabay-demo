package com.app.pixabay.search.di

import com.app.pixabay.database.PixPayBackDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sqlDriverModule: Module =
    module {
        single<SqlDriver> {
            NativeSqliteDriver(PixPayBackDatabase.Schema, "PixPayBackDatabase.db")
        }
    }
