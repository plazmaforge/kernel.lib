// swift-tools-version:5.0
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "plazma",
    //platforms: [
    //    .macOS(.v10_14), .linux, .only
    //],
    products: [
        .executable(name: "run", targets: ["run"]),
        //.library(name: "lib-base", targets: ["lib"]),
        //.library(name: "lib-base-static", type: .static, targets: ["lib"]),
        //.library(name: "lib-base-dynamic", type: .dynamic, targets: ["lib"])
    ],
    dependencies: [
        // Dependencies declare other packages that this package depends on.
        // .package(url: /* package url */, from: "1.0.0"),
    ],
    targets: [
        .target(
            name: "run",
            dependencies: [],
            path: "src/main/swift"),
        .testTarget(
            name: "test",
            dependencies: ["run"],
            path: "src/test/swift")
    ]
)
