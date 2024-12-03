package com.vlaskorobogatov.advent_of_code.tasks

import com.vlaskorobogatov.advent_of_code.readFrom
import kotlinx.io.files.Path
import kotlinx.io.readLine
import kotlin.math.abs

fun main() {
    val parentFolder = Path("src/main/resources")
    val path = Path(parentFolder, "2.txt")

    val levels = mutableListOf<List<Int>>()
    path.readFrom {
        while (true) {
            val line = readLine() ?: break
            val level = line.split(" ").map(String::toInt)
            levels.add(level)
        }
    }

    part1(levels)
    part2(levels)
}

fun part1(levels: List<List<Int>>) {
    val level = levels.map(::isListSafe).count { it }

    println(level)
}

fun part2(levels: List<List<Int>>) {
    val safeLevels = levels.map { level ->
        if (isListSafe(list = level)) return@map true
        level.withIndex().any { (index, _) ->
            isListSafe(list = level.filterIndexed { indexNew, _ -> index != indexNew })
        }
    }.count { it }

    println(safeLevels)
}

fun isListSafe(list: List<Int>): Boolean {
    val shouldIncrease = list[0] < list[1]
    val isSafe = list.withIndex().all { (index, i) ->
        if (index == 0) return@all true
        val prev = list[index - 1]
        when (shouldIncrease) {
            true -> i > prev
            else -> i < prev
        } && abs(i - prev) in 1..3
    }
    return isSafe
}