package com.example

import java.io.File
import java.io.InputStream

// Get the character's ascii code and convert it to the right number
fun getValue(c: Char): Int {
  return if ('a' <= c && c <= 'z') (c.code - 96) else c.code - 64 + 26
}

fun part1Solution(inputStream: InputStream) {
  var leftIndex: Int
  var rightIndex: Int
  var leftChar: Char
  var rightChar: Char
  var leftSet: MutableSet<Char>
  var rightSet: MutableSet<Char>
  // key is letter, value is # times it appears in both compartments of a backpack
  var counts = mutableMapOf<Char, Int>()

  inputStream.bufferedReader().forEachLine {
    leftSet = mutableSetOf<Char>()
    rightSet = mutableSetOf<Char>()
    leftIndex = 0
    rightIndex = it.length - 1

    lineLoop@ while (leftIndex < rightIndex) {
      leftChar = it[leftIndex]
      rightChar = it[rightIndex]
      leftSet.add(leftChar)
      rightSet.add(rightChar)

      if (leftSet.contains(rightChar)) {
        counts.set(rightChar, 1 + (counts.get(rightChar) ?: 0))
        break@lineLoop
      }

      if (rightSet.contains(leftChar)) {
        counts.set(leftChar, 1 + (counts.get(leftChar) ?: 0))
        break@lineLoop
      }

      leftIndex++
      rightIndex--
    }
  }

  println(counts.entries.fold(0) { sum, item -> sum + item.value * getValue(item.key) })
}

fun part2Solution() {}

/* args: Array<String> */
fun main() {
  val inputStream = File("advent/day3/input.txt").inputStream()
  part1Solution(inputStream)
}
