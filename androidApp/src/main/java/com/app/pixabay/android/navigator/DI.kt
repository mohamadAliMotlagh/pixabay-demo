package com.app.pixabay.android.navigator

import org.koin.dsl.module

val navigator =
    module {
        single<Navigator> { NavigatorImpl() }
    }