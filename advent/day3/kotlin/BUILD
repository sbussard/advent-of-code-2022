load("@io_bazel_rules_kotlin//kotlin:jvm.bzl", "kt_jvm_binary")

kt_jvm_binary(
    name = "bin",
    srcs = glob(["*.kt"]),
    data = ["//advent/day3:files"],
    main_class = "com.example.BinKt",
)
