//
//  GDCUIView.swift
//  Threads
//
//  Created by Mark Joselli on 14/08/23.
//

import SwiftUI

struct OperationQueueUIView: View {
    @State private var isLoading = false
    @State private var result = ""
        
    var body: some View {
        VStack {
            Text("OperationQueue Example")
                .font(.headline)
            
            Button("Fetch Data") {
                isLoading = true
                let operationQueue = OperationQueue()
                operationQueue.addOperation {
                    let asyncResult = fetchDataInBackground()
                    OperationQueue.main.addOperation {
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

struct OperationQueueUIView_Previews: PreviewProvider {
    static var previews: some View {
        OperationQueueUIView()
    }
}
