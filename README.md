# Solutions to Advent of Code 2022

[Background](https://adventofcode.com/2022)

## What am I looking at?

This repo is a [bazel](https://bazel.build/) project... just because.

## It doesn't work for me

Create an issue and I'll try to help if I can. Bazel is very advanced, which makes it hard to use sometimes.

## This code is ugly / hard to understand

Besides that obvious reasons about my abilities, there are more considerations:

1. High-level code is easier to understand than low-level code  
2. Code may be designed more for performance than aesthetics  
3. Code may showcase a particular data structure or algorithm  
4. Workspace may not work with latest language features  

## Solutions

| Day | Part | Command to Run (from the project root)                                                  |
| --- | ---- | --------------------------------------------------------------------------------------- |
| 1   | 1    | `bazel run //advent/day1/cc:bin -- --file_path="$(pwd)/advent/day1/input.txt" --part=1` |
| 1   | 2    | `bazel run //advent/day1/cc:bin -- --file_path="$(pwd)/advent/day1/input.txt" --part=2` |