workspace(name = "advent_of_code_2022")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

# CC dependencies -------------------------------------------------------------

http_archive(
    name = "com_google_absl",
    sha256 = "aabf6c57e3834f8dc3873a927f37eaf69975d4b28117fc7427dfb1c661542a87",
    strip_prefix = "abseil-cpp-98eb410c93ad059f9bba1bf43f5bb916fc92a5ea",
    urls = ["https://github.com/abseil/abseil-cpp/archive/98eb410c93ad059f9bba1bf43f5bb916fc92a5ea.zip"],
)

# Deno ------------------------------------------------------------------------

http_archive(
    name = "aspect_rules_deno",
    sha256 = "cfda7aeb308082a4525f391b66e81d4f15bd05c3f0a5131e4645e74ea1e32760",
    strip_prefix = "rules_deno-0.3.0",
    url = "https://github.com/aspect-build/rules_deno/archive/refs/tags/v0.3.0.tar.gz",
)

load(
    "@aspect_rules_deno//deno:repositories.bzl",
    "LATEST_VERSION",
    "deno_register_toolchains",
    "rules_deno_dependencies",
)

# Fetches the rules_deno dependencies.
# If you want to have a different version of some dependency,
# you should fetch it *before* calling this.
# Alternatively, you can skip calling this function, so long as you've
# already fetched all the dependencies.
rules_deno_dependencies()

deno_register_toolchains(
    name = "deno",
    deno_version = LATEST_VERSION,
)

# Kotlin ----------------------------------------------------------------------

rules_kotlin_version = "1.6.0"

rules_kotlin_sha = "a57591404423a52bd6b18ebba7979e8cd2243534736c5c94d35c89718ea38f94"

http_archive(
    name = "io_bazel_rules_kotlin",
    sha256 = rules_kotlin_sha,
    urls = ["https://github.com/bazelbuild/rules_kotlin/releases/download/v%s/rules_kotlin_release.tgz" % rules_kotlin_version],
)

load("@io_bazel_rules_kotlin//kotlin:repositories.bzl", "kotlin_repositories")

kotlin_repositories()

load("@io_bazel_rules_kotlin//kotlin:core.bzl", "kt_register_toolchains")

kt_register_toolchains()
