package com.vlaskorobogatov.advent_of_code.tasks

import com.vlaskorobogatov.advent_of_code.readFrom
import kotlinx.io.files.Path
import kotlinx.io.readLine
import kotlin.math.abs

fun main() {
    val parentFolder = Path("src/main/resources")
    val path = Path(parentFolder, "1.txt")

    val listFirst = mutableListOf<Int>()
    val listSecond = mutableListOf<Int>()
    path.readFrom {
        while (true) {
            val line = readLine() ?: break
            val pair = line.split("   ")
            listFirst.add(pair[0].toInt())
            listSecond.add(pair[1].toInt())
        }
    }

    part1(listFirst.sorted(), listSecond.sorted())
    part2(listFirst, listSecond)
}

fun part1(listFirst: List<Int>, listSecond: List<Int>) {
    listFirst.zip(listSecond).sumOf { (first, second) ->
        abs(first - second)
    }.let(::println)
}

fun part2(listFirst: List<Int>, listSecond: List<Int>) {
    val occurrences = listSecond
        .groupingBy { it }
        .eachCount()

    listFirst.sumOf {
        it * (occurrences[it] ?: 0)
    }.let(::println)
}
