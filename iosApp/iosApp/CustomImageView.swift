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
        WebImage(url: URL(string: imageModel.thumbnail))
            .resizable()
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .aspectRatio(CGFloat(imageModel.ratio), contentMode: .fit)
            .mask(RoundedRectangle(cornerRadius: 16))
    }
}
