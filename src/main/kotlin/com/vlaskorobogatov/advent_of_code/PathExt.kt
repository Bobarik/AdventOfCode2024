package com.vlaskorobogatov.advent_of_code

import kotlinx.io.Source
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem

fun <T> Path.readFrom(
    block: Source.() -> T
) = SystemFileSystem.source(this).use { fileSource ->
    fileSource.buffered().use { bufferedFileSource ->
        bufferedFileSource.block()
    }
}