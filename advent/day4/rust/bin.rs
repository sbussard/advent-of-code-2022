use std::fs::File;
use std::io::{self, prelude::*, BufReader};

fn split_once(in_string: &str, d: char) -> [&str; 2] {
    let mut splitter = in_string.splitn(2, d);
    let first = splitter.next().unwrap();
    let second = splitter.next().unwrap();
    return [first, second];
}

fn check_overlap(elf_a: [i8; 2], elf_b: [i8; 2]) -> bool {
    let elf_a_includes_b = elf_a[0] <= elf_b[0] && elf_b[1] <= elf_a[1];
    let elf_b_includes_a = elf_b[0] <= elf_a[0] && elf_a[1] <= elf_b[1];
    return elf_a_includes_b || elf_b_includes_a;
}

fn solution_part_1() -> io::Result<()> {
    let file = File::open("advent/day4/input.txt")?;
    let reader = BufReader::new(file);
    let mut overlaps = 0;

    for line_ in reader.lines() {
        let line = line_?;
        let [elf_a, elf_b] = split_once(&line, ',');
        if check_overlap(
            split_once(&elf_a, '-').map(|c| c.parse::<i8>().unwrap()),
            split_once(&elf_b, '-').map(|c| c.parse::<i8>().unwrap()),
        ) {
            overlaps += 1;
        }
    }

    println!("{:?}", overlaps);
    Ok(())
}

fn main() {
    solution_part_1().unwrap();
}
