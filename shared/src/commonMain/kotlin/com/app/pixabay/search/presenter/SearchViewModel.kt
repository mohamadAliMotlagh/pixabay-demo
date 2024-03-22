package com.app.pixabay.search.presenter

import com.app.pixabay.core.viewmodel.BaseViewModel
import com.app.pixabay.search.domain.SearchRepository
import kotlinx.coroutines.launch

class SearchViewModel(repository: SearchRepository) : BaseViewModel() {
    init {
        coroutineScope.launch {
            repository.search("test")
        }
    }
}
