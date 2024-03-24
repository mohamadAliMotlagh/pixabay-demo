import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init(){
        SearchDI_iosKt.doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
