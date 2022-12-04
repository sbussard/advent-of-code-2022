package day3

import java.io.InputStream

fun visualizeHeader() {
  println()
  println("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".split("").joinToString(" ").trim())
}

fun visualizeRow(row: Array<Int>, bag: String) {
  println(row.map { if (0 < it) it else " " }.joinToString(" ") + " | " + bag)
}

fun part2Solution(inputStream: InputStream) {
  // tabulation with letters as columns and bags as rows
  // for each bag (row), for each letter (column),
  // the value at [row][column] is 1 + [row - 1][column] unless row == 0
  // when it adds up to 3 then the letter is in all the bags
  // It's a 3 x 52 Array.

  val groupSize = 3
  var tabulation = Array(groupSize) { Array<Int>(52) { 0 } }
  var sum = 0
  val reader = inputStream.bufferedReader()
  var bag: String
  var i = 0
  var column: Int
  var bagNumber: Int

  while (true) {
    bag = reader.readLine() ?: break
    bagNumber = i % groupSize
    tabulation[bagNumber] = Array<Int>(52) { 0 }

    when (bagNumber) {
      0 -> {
        for (item in bag) {
          column = getValue(item) - 1
          tabulation[bagNumber][column] = 1
        }
        // Enable next line to see what's going on
        // visualizeHeader()
      }
      (groupSize - 1) -> {
        bagLoop@ for (item in bag) {
          column = getValue(item) - 1
          tabulation[bagNumber][column] = 1 + tabulation[bagNumber - 1][column]
          if (tabulation[bagNumber][column] == groupSize) {
            sum += column + 1
            break@bagLoop
          }
        }
      }
      else -> {
        for (item in bag) {
          column = getValue(item) - 1
          tabulation[bagNumber][column] = 1 + tabulation[bagNumber - 1][column]
        }
      }
    }

    // Enable next line to see what's going on
    // visualizeRow(tabulation[bagNumber], bag)

    i++
  }
  reader.close()
  println(sum)
}
