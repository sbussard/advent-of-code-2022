#include <iostream>

#include "absl/flags/flag.h"
#include "absl/flags/parse.h"
#include "advent/day1/cc/lib.h"

ABSL_FLAG(std::string, file_path, "", "full file path for input file");
ABSL_FLAG(int, part, 0, "which part to execute (1 or 2)");

int main(int argc, char *argv[]) {
  absl::ParseCommandLine(argc, argv);
  const std::string file_path = absl::GetFlag(FLAGS_file_path);
  switch (absl::GetFlag(FLAGS_part)) {
    case 1: return advent::Part1Solution(file_path);
    case 2: return advent::Part2Solution(file_path);
    default: return 1;
  }
  return 0;
}