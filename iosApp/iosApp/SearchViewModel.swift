//
//  SearchViewModel.swift
//  iosApp
//
//  Created by hosein on 3/24/24.
//  Copyright © 2024 orgName. All rights reserved.
//
import shared
import Foundation


class SearchViewModel: ObservableObject {
    let searchRepo = InjectHelper()
    @Published var searchResults: [SearchResultDomainModel] = []
    @Published var errorMessage: String?
    
    func search(query: String) {
        searchRepo.search(query: query) { items in
            self.searchResults = items
        }
    }
}
