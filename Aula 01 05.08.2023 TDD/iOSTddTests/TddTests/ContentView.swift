//
//  ContentView.swift
//  TddTests
//
//  Created by Mark Joselli on 03/08/23.
//

import SwiftUI

struct ContentView: View {
    @State private var num1 = 0
    @State private var num2 = 0

    @State var result: Int = 0
    var body: some View {
        
        VStack {
            TextField("Enter first number", value: $num1, format: .number)
                            .textFieldStyle(.roundedBorder)
                            .padding()
                            .accessibilityIdentifier("num1TextField")
            TextField("Enter second number", value: $num2, format: .number)
                            .textFieldStyle(.roundedBorder)
                            .padding()
                            .accessibilityIdentifier("num2TextField")
            Button(action: {
                result = SumCalculator.calculate(num1,num2)
            }, label: {
                Text("Add")
            })
            .accessibilityIdentifier("addButton")
            Text("Result: \(result)")
                .accessibilityIdentifier("resultText")
        }
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
