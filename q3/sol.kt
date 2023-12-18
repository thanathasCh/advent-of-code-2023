package q3

import readFile

fun List<List<Char>>.get(x: Int, y: Int): Char =
    this.getOrNull(x)?.getOrNull(y) ?: '.'

fun Char.isSymbol(): Boolean =
    this != '.' && !this.isLetterOrDigit()

fun Char.isAsterisk(): Boolean = this == '*'

private fun isValid(map: List<List<Char>>, row: Int, first: Int, second: Int): Boolean {
    if (map.get(row, first - 1).isSymbol() || map.get(row, second + 1).isSymbol()) {
        return true
    }

    for (y in (first - 1)..(second + 1)) {
        if (map.get(row - 1, y).isSymbol()) {
            return true
        }

        if (map.get(row + 1, y).isSymbol()) {
            return true
        }
    }

    return false
}

private fun findAsterisk(map: List<List<Char>>, row: Int, first: Int, second: Int): List<Pair<Int, Int>> {
    val list = mutableListOf<Pair<Int, Int>>()

    if (map.get(row, first - 1).isAsterisk()) {
        list.add(Pair(row, first - 1))
    }

    if (map.get(row, second + 1).isAsterisk()) {
        list.add(Pair(row, second + 1))
    }

    for (y in (first - 1)..(second + 1)) {
        if (map.get(row - 1, y).isAsterisk()) {
            list.add(Pair(row - 1, y))
        }

        if (map.get(row + 1, y).isAsterisk()) {
            list.add(Pair(row + 1, y))
        }
    }

    return list
}

fun first(data: List<String>): Int {
    val regex = "\\d+".toRegex()
    val map = data.map { it.toList() }

    return data.foldIndexed(0) { index, acc1, line ->
        val matches = regex.findAll(line)
        matches.fold(acc1) { acc2, match ->
            if (isValid(map, index, match.range.first, match.range.last)) {
                acc2 + match.value.toInt()
            } else {
                acc2
            }
        }
    }
}

fun second(data: List<String>): Int {
    val regex = "\\d+".toRegex()
    val map = data.map { it.toList() }
    val gearMap = mutableMapOf<Pair<Int, Int>, MutableList<Int>>()

    data.forEachIndexed { index, line ->
        val matches = regex.findAll(line)
        matches.forEach { match ->
            findAsterisk(map, index, match.range.first, match.range.last).forEach { pair ->
                if (gearMap.containsKey(pair)) {
                    gearMap[pair]!!.add(match.value.toInt())
                } else {
                    gearMap[pair] = mutableListOf(match.value.toInt())
                }
            }
        }
    }

    return gearMap
        .filter { it.value.size == 2 }
        .map {
            it.value[0] * it.value[1]
        }
        .sum()
}

fun main() {
//    val data = readFileExample(3)
    val data = readFile(3)
//    println(first(data))
    println(second(data))
}

