package com.app.pixabay.core.network

import org.koin.dsl.module


val ktorProvider = module {
    single<KtorApi> { KtorApiImpl() }
}