package something;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Set;
import java.util.HashSet;
import io.vavr.control.Try;

public class Bin {
    static void CountCharacters(String line, int markerLength) {
        // use sliding window approach
        int l = 0;
        int r = 0;
        int count = 0;
        // Alternatively could use a map to keep track of where a particular letter was first seen
        Set<Character> lettersInWindow = new HashSet<Character>();

        Character c;
        while (r < line.length()) {
            if (lettersInWindow.size() == markerLength) break;

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
                CountCharacters(line, 4);
                Part1(file);
            });
    }

    static void Part2(BufferedReader file) {
        Try.of(() -> file.readLine())
            .andThen(line -> {
                if (line == null) return;
                CountCharacters(line, 14);
                Part2(file);
            });
    }

    public static void main(String[] args) {
        Try.of(() -> new FileReader("advent/day6/input.txt"))
            .map(file -> new BufferedReader(file))
            .andThen(file -> {
                if (args.length < 1 || args[0].equals("1")) Part1(file);
                else Part2(file);
            });
    }
}