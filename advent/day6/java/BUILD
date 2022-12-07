# java support is built in to bazel

java_binary(
    name = "bin",
    srcs = glob(["*.java"]),
    data = ["//advent/day6:files"],
    main_class = "something.Bin",
    deps = ["@maven//:io_vavr_vavr"],
)
