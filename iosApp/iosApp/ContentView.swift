//
//  ContentView.swift
//  iosApp
//
//  Created by MohamadAli (Hosein) Motlagh on 5/16/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import shared
import SDWebImageSwiftUI
import SwiftUI

struct ContentView: View {
    @StateObject var viewModel = SearchViewModel()

    let spacing: CGFloat = 12

    var body: some View {
        NavigationView {
            ScrollView {
                StaggeredGrid {
                    ForEach(viewModel.searchResults, content: CustomImageView.init)
                }
                .padding(.horizontal)
            }.navigationTitle("Pixabay Photos")
        }.searchable(text: $viewModel.value)
    }
}

struct ContentView_Preview: PreviewProvider {
    static var previews: some View{
        ContentView()
    }
}
