package com.app.pixabay.android.presenter.di

import com.app.pixabay.android.presenter.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchViewModelModule = module {
    viewModel { SearchViewModel(get(), get()) }
}