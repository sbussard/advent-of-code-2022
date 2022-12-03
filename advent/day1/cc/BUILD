cc_library(
    name = "lib",
    srcs = ["lib.cc"],
    hdrs = ["lib.h"],
    deps = ["@com_google_absl//absl/strings"],
)

cc_binary(
    name = "bin",
    srcs = ["bin.cc"],
    deps = [
        ":lib",
        "@com_google_absl//absl/flags:flag",
        "@com_google_absl//absl/flags:parse",
        "@com_google_absl//absl/strings",
    ],
)
