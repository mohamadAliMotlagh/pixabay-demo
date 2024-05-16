//
//  SearchViewModel.swift
//  iosApp
//
//  Created by hosein on 3/24/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import shared
import Foundation
import Combine

class SearchViewModel: ObservableObject {
    let searchRepo = InjectHelper()
    @Published var searchResults1: [SearchResultDomainModel] = []
    @Published var searchResults2: [SearchResultDomainModel] = []
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
            self.searchResults1.removeAll()
            self.searchResults2.removeAll()
            for (index, item) in items.enumerated() {
                if index % 2 == 0 {
                    self.searchResults1.append(item)
                } else {
                    self.searchResults2.append(item)
                }
            }
        } failure: { kotlinThrowable in
            
        }
    }
}
