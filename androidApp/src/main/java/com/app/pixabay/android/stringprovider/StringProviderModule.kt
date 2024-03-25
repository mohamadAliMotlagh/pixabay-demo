package com.app.pixabay.android.stringprovider

import org.koin.dsl.module

val stringProviderModule = module {
    single<StringProvider> { StringProviderImpl(get()) }
}