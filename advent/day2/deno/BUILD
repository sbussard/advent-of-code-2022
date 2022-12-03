load("@aspect_rules_deno//deno:defs.bzl", "deno_binary")

deno_binary(
    name = "bin",
    allow = [
        "read",
        "write",
    ],
    main = "bin.ts",
    unstable_apis = True,
    deps = ["//advent/day2:files"],
)
