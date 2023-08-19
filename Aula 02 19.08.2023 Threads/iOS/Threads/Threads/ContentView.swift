//
//  ContentView.swift
//  Threads
//
//  Created by Mark Joselli on 14/08/23.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        NavigationView {
            
            VStack {
                NavigationLink(destination: DispatchQueueUIView()) {
                        Text("DispatchQueue")
                            .padding()
                            .background(Color.blue)
                            .foregroundColor(.white)
                            .cornerRadius(10)
                        }
                NavigationLink(destination: OperationQueueUIView()) {
                        Text("OperationQueue")
                            .padding()
                            .background(Color.blue)
                            .foregroundColor(.white)
                            .cornerRadius(10)
                        }
            }
            .padding()
        }.navigationTitle("Threads")
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
