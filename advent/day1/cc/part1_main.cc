#include <iostream>
#include <fstream>
#include <unordered_set>

#include "absl/flags/flag.h"
#include "absl/flags/parse.h"
#include "absl/strings/str_cat.h"
#include "absl/strings/string_view.h"

ABSL_FLAG(std::string, file_path, "", "full file path for input file");

namespace advent {
    // Don't @ Me https://cplusplus.github.io/LWG/issue3430
    void Process(std::string file_path) {
        std::ifstream TheFileStream(file_path);
        std::string line;
        // TODO this set is totally unnecessary 
        std::unordered_set<int> current_elf_numbers;
        int max_calories = -1;

        while (getline (TheFileStream, line)) {
            if (line != "") {
                current_elf_numbers.insert(std::stoi(line));
                continue;
            }

            int current_calories = 0;
            for (const auto& item: current_elf_numbers) {
                current_calories += item;
            }
            if (max_calories < current_calories) {
                max_calories = current_calories;
            }
            current_elf_numbers.clear();
        }
        TheFileStream.close();

        std::cout << max_calories << std::endl;
    }
} // namespace advent


int main(int argc, char *argv[]) {
    absl::ParseCommandLine(argc, argv);
    advent::Process(absl::GetFlag(FLAGS_file_path));
    return 0;
}