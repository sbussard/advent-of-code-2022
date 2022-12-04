package day3

import java.io.File

// Get the character's ascii code and convert it to the right number
fun getValue(c: Char): Int {
  return if ('a' <= c && c <= 'z') (c.code - 96) else c.code - 64 + 26
}

fun main(args: Array<String>) {
  val inputStream = File("advent/day3/input.txt").inputStream()
  when (args[0]) {
    "1" -> part1Solution(inputStream)
    "2" -> part2Solution(inputStream)
    else -> println("Invalid argument")
  }
}
