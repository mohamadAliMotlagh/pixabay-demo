//
//  SearchViewModel.swift
//  iosApp
//
//  Created by MohamadAli (Hosein) Motlagh on 3/24/24.
//  Copyright © 2024. All rights reserved.
//

import shared
import Foundation
import Combine

class SearchViewModel: ObservableObject {
    let searchRepo = InjectHelper()
    @Published var searchResults: [SearchResultDomainModel] = []
    @Published var errorMessage: String?
    @Published var value: String = "Fruits"
    private var cancellables = Set<AnyCancellable>()

    init() {
        $value
            .debounce(for: .seconds(0.5), scheduler: RunLoop.main) // Debounce to reduce search frequency
            .removeDuplicates() // Prevent searching for the same query multiple times
            .sink { [weak self] value in
                self?.search(query: value)
            }
            .store(in: &cancellables)
    }

    private func search(query: String) {
        searchRepo.search(query: query) { items in
            self.searchResults = items
        } failure: { kotlinThrowable in
            
        }
    }
}
