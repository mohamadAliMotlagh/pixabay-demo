import SwiftUI
import shared
import SDWebImageSwiftUI
import SwiftUI
    
struct CustomImageView: View {
    let imageModel: SearchResultDomainModel
    var body: some View {
        VStack {
                       WebImage(url: URL(string: imageModel.thumbnail))
                           .resizable()
                           .aspectRatio(CGFloat(imageModel.ratio), contentMode: .fit)
                           .clipped()
                           .cornerRadius(16)
                   
                   }
    }
}
