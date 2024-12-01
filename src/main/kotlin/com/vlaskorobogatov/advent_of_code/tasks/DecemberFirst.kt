package com.vlaskorobogatov.advent_of_code.tasks

import com.vlaskorobogatov.advent_of_code.readFrom
import kotlinx.io.files.Path
import kotlinx.io.readLine
import kotlin.math.abs

fun main() {
    val parentFolder = Path("src/main/resources")
    val path = Path(parentFolder, "input.txt")

    part1(path)
    part2(path)
}

fun part1(path: Path) {
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

    val lfs = listFirst.sorted()
    val lss = listSecond.sorted()
    var acc: Long = 0
    lfs.forEachIndexed { index, i ->
        acc += abs(i - lss[index])
    }

    println(acc)
}

fun part2(path: Path) {
    val listFirst = mutableListOf<Long>()
    val listSecond = mutableListOf<Long>()
    path.readFrom {
        while (true) {
            val line = readLine() ?: break
            val pair = line.split("   ")
            listFirst.add(pair[0].toLong())
            listSecond.add(pair[1].toLong())
        }
    }

    val occurrences = listSecond
        .groupBy { it }
        .mapValues { it.value.size }

    val acc = listFirst.fold(0L) { acc, l ->
        acc + l * (occurrences[l] ?: 0)
    }

    println(acc)
}
