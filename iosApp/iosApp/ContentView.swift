import SwiftUI
import shared
import SDWebImageSwiftUI
import SwiftUI

struct ContentView: View {
    @StateObject var viewModel = SearchViewModel()

    
    private let columns: [GridItem] = Array(repeating: GridItem(.flexible()), count: 2)


    var body: some View {
        NavigationView {
            ScrollView {
                LazyVGrid(columns: self.columns, spacing: 0) {
                    ForEach(viewModel.searchResults1, id: \.id) {model in
                            CustomImageView(imageModel: model)
                        }
                    }
                }.navigationTitle("Pixabay Photos")
            }
            .searchable(text: $viewModel.value)
            
        }
    }
    

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
/// TODO Placeholder
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
