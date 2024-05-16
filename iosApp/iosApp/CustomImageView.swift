//
//  SearchViewModel.swift
//  iosApp
//
//  Created by MohamadAli (Hosein) Motlagh on 3/24/24.
//  Copyright © 2024. All rights reserved.
//

import SwiftUI
import shared
import SDWebImageSwiftUI

struct CustomImageView: View {
    let imageModel: SearchResultDomainModel

    @State private var isLoaded = false

    var body: some View {
        Color(.quaternarySystemFill) // Placeholder
            .overlay {
                WebImage(url: URL(string: imageModel.thumbnail))
                    .resizable()
                    .onSuccess { _,_,_  in isLoaded = true }
                    .aspectRatio(contentMode: .fit)
                    .animation(.default, value: isLoaded)
            }
            .aspectRatio(CGFloat(imageModel.ratio), contentMode: .fill)
            .mask(RoundedRectangle(cornerRadius: 16))
    }
}
