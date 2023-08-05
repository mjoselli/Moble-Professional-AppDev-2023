//
//  TddTestsUITests.swift
//  TddTestsUITests
//
//  Created by Mark Joselli on 03/08/23.
//

import XCTest

final class TddTestsUITests: XCTestCase {
    let app = XCUIApplication()
    override func setUpWithError() throws {
        // Put setup code here. This method is called before the invocation of each test method in the class.
        app.launch()
        // In UI tests it is usually best to stop immediately when a failure occurs.
        continueAfterFailure = false

        // In UI tests it’s important to set the initial state - such as interface orientation - required for your tests before they run. The setUp method is a good place to do this.
    }

    override func tearDownWithError() throws {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testExample() throws {
        // UI tests must launch the application that they test.
        //let app = XCUIApplication()
        //app.launch()
        let addButton = app.buttons["addButton"]
        XCTAssert(addButton.exists)
        XCTAssertEqual(addButton.label, "Add")

        
        // Use XCTAssert and related functions to verify your tests produce the correct results.
    }
    
    func testExample2() throws {
        app.textFields["num1TextField"].tap()
        app.textFields["num1TextField"].typeText("3")
        app.textFields["num2TextField"].tap()
        app.textFields["num2TextField"].typeText("4")
        app.buttons["addButton"].tap()
        XCTAssertEqual(app.staticTexts["resultText"].label,"Result: 7")
    }

    func testLaunchPerformance() throws {
        if #available(macOS 10.15, iOS 13.0, tvOS 13.0, watchOS 7.0, *) {
            // This measures how long it takes to launch your application.
            measure(metrics: [XCTApplicationLaunchMetric()]) {
                XCUIApplication().launch()
            }
        }
    }
}
