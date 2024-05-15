//
//  StaggeredGrid.swift
//  iosApp
//
//  Created by hosein on 5/16/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import shared
import SDWebImageSwiftUI
import SwiftUI

struct StaggeredGrid: View {
    @StateObject var viewModel = SearchViewModel()

    
    private let columns: [GridItem] = Array(repeating: GridItem(.flexible()), count: 2)
    
    let spacing: CGFloat = 12
    var body: some View{
        NavigationView {
            ScrollView{
                HStack(alignment: .top,spacing: spacing) {
                    LazyVStack(content: {
                        ForEach(viewModel.searchResults1, id: \.id) {model in
                            CustomImageView(imageModel: model)
                        }
                    })
                    LazyVStack(content: {
                        ForEach(viewModel.searchResults2, id: \.id) {model in
                            CustomImageView(imageModel: model)
                        }
                    })
                    
                }.padding(10)
            }.navigationTitle("Pixabay Photos")
        } .searchable(text: $viewModel.value)
    }
    
}

struct StaggeredGrid_Preview: PreviewProvider {
    static var previews: some View{
        StaggeredGrid()
    }
}
