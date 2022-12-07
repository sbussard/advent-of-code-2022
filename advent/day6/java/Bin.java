package something;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Set;
import java.util.HashSet;
import io.vavr.control.Try;

public class Bin {
    static void CountCharacters(String line) {
        // use sliding window approach
        int l = 0;
        int r = 0;
        int count = 0;
        // Alternatively could use a map to keep track of where a particular letter was first seen
        Set<Character> lettersInWindow = new HashSet<Character>();

        Character c;
        while (r < line.length()) {
            if (lettersInWindow.size() == 4) break;

            c = line.charAt(r);
            if (lettersInWindow.contains(c)) {
                while (line.charAt(l) != c) {
                    lettersInWindow.remove(line.charAt(l));
                    l++;
                }
                l++;
            }
            lettersInWindow.add(c);

            r++;
            count++;
        }

        System.out.println(count);
    }

    static void Part1(BufferedReader file) {
        Try.of(() -> file.readLine())
            .andThen(line -> {
                if (line == null) return;
                CountCharacters(line);
                Part1(file);
            });
    }

    public static void main(String[] args) {
        Try.of(() -> new FileReader("advent/day6/input.txt"))
            .map(file -> new BufferedReader(file))
            .andThen(Bin::Part1);
    }
}