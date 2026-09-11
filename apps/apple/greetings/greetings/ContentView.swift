//
//  ContentView.swift
//  greetings
//
//  Created by Dushyant Suthar on 11/09/26.
//

import SwiftUI
import Greetings

struct ContentView: View {
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)
            Text(Greetings().greet())
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
