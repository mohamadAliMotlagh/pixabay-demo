import SwiftUI
import shared

import SwiftUI

struct ContentView: View {
    @StateObject var viewModel = SearchViewModel()
    @State private var query = "fruit"

    var body: some View {
        NavigationView {
            List(viewModel.searchResults, id: \.id) { item in
                VStack{
                    AsyncImage(url: URL(string: item.thumbnail))
                    Text(item.name)
                }
                
            }
            .navigationBarTitle("Search")
            .navigationBarItems(trailing: Button("Search") {
                viewModel.search(query: query)
            })
            .searchable(text: $query)
        }
    }
    
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
