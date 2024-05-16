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

    var body: some View {
        Color(.quaternarySystemFill) // Placeholder
            .overlay {
                WebImage(url: URL(string: imageModel.thumbnail))
                    .resizable()
                    .aspectRatio(contentMode: .fit)
            }
            .aspectRatio(CGFloat(imageModel.ratio), contentMode: .fill)
            .mask(RoundedRectangle(cornerRadius: 16))
    }
}
