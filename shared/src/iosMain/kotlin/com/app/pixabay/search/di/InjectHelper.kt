package com.app.pixabay.search.di

import com.app.pixabay.search.domain.SearchRepository
import com.app.pixabay.search.domain.SearchUseCase
import com.app.pixabay.search.domain.model.SearchResultDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class InjectHelper : KoinComponent {
    private val usecase: SearchUseCase by inject()
    private val scope = CoroutineScope(Dispatchers.Main)

    fun search(
        query: String,
        success: (List<SearchResultDomainModel>) -> Unit,
    ) {
        scope.launch {
            success(usecase(query).getOrDefault(listOf()))
        }
    }
}