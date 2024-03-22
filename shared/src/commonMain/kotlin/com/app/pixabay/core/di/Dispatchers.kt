package com.app.pixabay.core.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val ioDispatcher: String = "IODispatcher"
const val mainDispatcher: String = "mainDispatcher"

val dispatchersModule =
    module {
        single<CoroutineDispatcher>(named(ioDispatcher)) {
            Dispatchers.IO
        }

        single<CoroutineDispatcher>(named(mainDispatcher)) {
            Dispatchers.Main
        }
    }
