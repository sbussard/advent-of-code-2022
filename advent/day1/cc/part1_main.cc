#include <iostream>
#include <fstream>

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

        std::cout << max_calories << std::endl;
    }
} // namespace advent


int main(int argc, char *argv[]) {
    absl::ParseCommandLine(argc, argv);
    advent::Process(absl::GetFlag(FLAGS_file_path));
    return 0;
}