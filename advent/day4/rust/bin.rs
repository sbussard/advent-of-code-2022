use std::env;
use std::fs::File;
use std::io::{self, prelude::*, BufReader};

fn split_once(in_string: &str, d: char) -> [&str; 2] {
    let mut splitter = in_string.splitn(2, d);
    let first = splitter.next().unwrap();
    let second = splitter.next().unwrap();
    return [first, second];
}

fn check_overlap(a: [i8; 2], b: [i8; 2]) -> bool {
    let b_start_during_a = a[0] <= b[0] && b[0] <= a[1];
    let b_end_during_a = a[0] <= b[1] && b[1] <= a[1];
    let a_start_during_b = b[0] <= a[0] && a[0] <= b[1];
    let a_end_during_b = b[0] <= a[1] && a[1] <= b[1];

    return b_start_during_a || b_end_during_a || a_start_during_b || a_end_during_b;
}

fn check_full_overlap(elf_a: [i8; 2], elf_b: [i8; 2]) -> bool {
    let elf_a_includes_b = elf_a[0] <= elf_b[0] && elf_b[1] <= elf_a[1];
    let elf_b_includes_a = elf_b[0] <= elf_a[0] && elf_a[1] <= elf_b[1];
    return elf_a_includes_b || elf_b_includes_a;
}

fn solution_part_1(file_path: &str) -> io::Result<()> {
    let file = File::open(file_path)?;
    let reader = BufReader::new(file);
    let mut overlaps = 0;

    for line_ in reader.lines() {
        let line = line_?;
        let [elf_a, elf_b] = split_once(&line, ',');
        if check_full_overlap(
            split_once(&elf_a, '-').map(|c| c.parse::<i8>().unwrap()),
            split_once(&elf_b, '-').map(|c| c.parse::<i8>().unwrap()),
        ) {
            overlaps += 1;
        }
    }

    println!("{:?}", overlaps);
    Ok(())
}
fn solution_part_2(file_path: &str) -> io::Result<()> {
    let file = File::open(file_path)?;
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

fn main() -> io::Result<()> {
    let args: Vec<String> = env::args().collect();
    let file_path = "advent/day4/input.txt";
    let part = if args.len() < 2 { "1" } else { &*args[1] };

    if part == "1" {
        solution_part_1(file_path)?;
    }
    if part == "2" {
        solution_part_2(file_path)?;
    }

    Ok(())
}
