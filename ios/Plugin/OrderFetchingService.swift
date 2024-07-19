import Foundation

@objc public class OrderFetchingService: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
