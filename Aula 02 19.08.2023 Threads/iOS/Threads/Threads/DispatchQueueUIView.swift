//
//  DispatchQueueUIView.swift
//  Threads
//
//  Created by Mark Joselli on 14/08/23.
//

import SwiftUI

struct DispatchQueueUIView: View {
    @State private var isLoading = false
    @State private var result = ""
    var body: some View {
        VStack {
                    Text("DispatchQueue Example")
                        .font(.headline)
                    
                    Button("Fetch Data") {
                        isLoading = true
                        DispatchQueue.global().async {
                            let asyncResult = fetchDataInBackground()
                            DispatchQueue.main.async {
                                result = asyncResult
                                isLoading = false
                            }
                        }
                    }
                    
                    if isLoading {
                        ProgressView()
                    } else {
                        Text(result)
                    }
                }
                .padding()
    }
    private func fetchDataInBackground() -> String {
            Thread.sleep(forTimeInterval: 3)
            return "Data fetched!"
        }
}

struct DispatchQueueUIView_Previews: PreviewProvider {
    static var previews: some View {
        DispatchQueueUIView()
    }
}
