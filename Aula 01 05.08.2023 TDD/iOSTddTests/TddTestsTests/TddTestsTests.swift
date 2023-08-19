//
//  TddTestsTests.swift
//  TddTestsTests
//
//  Created by Mark Joselli on 03/08/23.
//

import XCTest
@testable import TddTests

final class TddTestsTests: XCTestCase {

    override func setUpWithError() throws {
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }

    override func tearDownWithError() throws {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testExample() throws {
        // This is an example of a functional test case.
        // Use XCTAssert and related functions to verify your tests produce the correct results.
        // Any test you write for XCTest can be annotated as throws and async.
        // Mark your test throws to produce an unexpected failure when your test encounters an uncaught error.
        // Mark your test async to allow awaiting for asynchronous code to complete. Check the results with assertions afterwards.
        XCTAssertEqual(SumCalculator.calculate(2, 4), 6, "Sum is not working")
        XCTAssertEqual(MinusCalculator.calculate(2, 4), -2, "Minus is not working")
        
    }
    
    func testContentViewShouldExist() throws{
        let _ = ContentView()
    }

    func testPerformanceExample() throws {
        // This is an example of a performance test case.
        self.measure {
            do {
                sleep(4)
            }
        }
    }

}
