# Solutions to Advent of Code 2022

[Background](https://adventofcode.com/2022)

## What am I looking at?

This repo is a [bazel](https://bazel.build/) project... just because.

## Feedback

Please leave feedback under the corresponding [github issue](https://github.com/sbussard/advent-of-code-2022/issues).  
If one doesn't exist for your topic, please create a new one.  

## It doesn't work for me

Create an issue and I'll try to help if I can. Bazel is very advanced, which makes it hard to use sometimes.

## This code is ugly / hard to understand

Besides that obvious reasons about my abilities, there are more considerations:

1. High-level code is easier to understand than low-level code  
2. Code may be designed more for performance than aesthetics  
3. Code may showcase a particular data structure or algorithm  
4. Workspace may not work with latest language features  

## Solutions

| Day | Part | Language   | Command to Run (from the project root)                                                  |
| --- | ---- | ---------- | --------------------------------------------------------------------------------------- |
| 1   | 1    | C++        | `bazel run //advent/day1/cc:bin -- --file_path="$(pwd)/advent/day1/input.txt" --part=1` |
| 1   | 2    | C++        | `bazel run //advent/day1/cc:bin -- --file_path="$(pwd)/advent/day1/input.txt" --part=2` |
| 2   | 1    | TypeScript | `bazel run //advent/day2/deno:bin -- --part=1`                                          |
| 2   | 2    | TypeScript | `bazel run //advent/day2/deno:bin -- --part=2`                                          |
| 3   | 1    | Kotlin     | `bazel run //advent/day3/kotlin:bin -- 1`                                               |
| 3   | 2    | Kotlin     | `bazel run //advent/day3/kotlin:bin -- 2`                                               |
| 4   | 1    | Rust       | `bazel run //advent/day4/rust:bin -- 1`                                                 |
| 4   | 2    | Rust       | `bazel run //advent/day4/rust:bin -- 2`                                                 |
| 5   | 1    | Python     | `bazel run //advent/day5/python:bin -- 1`                                               |
| 5   | 2    | Python     | `bazel run //advent/day5/python:bin -- 2`                                               |
| 6   | 1    | Java       | `bazel run //advent/day6/java:bin`                                                      |
