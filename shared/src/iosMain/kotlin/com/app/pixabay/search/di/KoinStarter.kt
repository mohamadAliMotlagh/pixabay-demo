package com.app.pixabay.search.di

import com.app.pixabay.core.di.dispatchersModule
import com.app.pixabay.core.network.ktorProvider
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            ktorProvider,
            dispatchersModule,
        )
        modules(searchModules())
    }
}
