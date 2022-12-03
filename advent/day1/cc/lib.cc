#include <iostream>
#include <queue>
#include <fstream>
#include <string>

namespace advent {
  // Don't @ Me https://cplusplus.github.io/LWG/issue3430
  int Part1Solution(const std::string file_path) {
    std::ifstream TheFileStream(file_path);
    std::string line;
    int current_elf_calories = 0;
    int max_calories = -1;

    while (getline (TheFileStream, line)) {
      if (line != "") {
        current_elf_calories += std::stoi(line);
        continue;
      }

      if (max_calories < current_elf_calories) {
        max_calories = current_elf_calories;
      }
      current_elf_calories = 0;
    }
    TheFileStream.close();

    std::cout << std::to_string(max_calories) << std::endl;

    return 0;
  }

  int Part2Solution(const std::string file_path) {
    std::ifstream TheFileStream(file_path);
    std::string line;
    int current_elf_calories = 0;
    std::priority_queue<int> pq; // TODO limit to size 3

    while (getline (TheFileStream, line)) {
      if (line != "") {
        current_elf_calories += std::stoi(line);
        continue;
      }

      pq.push(current_elf_calories);
      current_elf_calories = 0;
    }
    TheFileStream.close();

    int top_3_combined_calories = 0;
    for (int i = 0; i < 3; i++) {
      top_3_combined_calories += pq.top();
      pq.pop();
    }

    std::cout << std::to_string(top_3_combined_calories) << std::endl;

    return 0;
  }
} // namespace advent::cc