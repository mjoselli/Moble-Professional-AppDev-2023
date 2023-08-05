//
//  TddTestsUITestsLaunchTests.swift
//  TddTestsUITests
//
//  Created by Mark Joselli on 03/08/23.
//

import XCTest

final class TddTestsUITestsLaunchTests: XCTestCase {

    override class var runsForEachTargetApplicationUIConfiguration: Bool {
        true
    }

    override func setUpWithError() throws {
        continueAfterFailure = false
    }
    
    func testLaunch() throws {
        let app = XCUIApplication()
        app.launch()

        // Insert steps here to perform after app launch but before taking a screenshot,
        // such as logging into a test account or navigating somewhere in the app
        app.textFields["num1TextField"].tap()
        app.textFields["num1TextField"].typeText("3")
        app.textFields["num2TextField"].tap()
        app.textFields["num2TextField"].typeText("4")
        app.buttons["addButton"].tap()

        let attachment = XCTAttachment(uniformTypeIdentifier: "public.png",
                                       name: "Screenshot-\(UIDevice.current.name)-\(name).png",
                                       payload: app.screenshot().pngRepresentation,
                                       userInfo: nil)
        attachment.name = "Added"
        attachment.lifetime = .keepAlways
        add(attachment)
    }
}
