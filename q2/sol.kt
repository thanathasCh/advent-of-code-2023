package q2

import readFile

//12 red cubes, 13 green cubes, and 14 blue cubes?

private fun formatValue(data: List<String>): List<List<Pair<Int, String>>> = data.map { first ->
    first.split(": ")[1].split(";")
        .flatMap { second ->
            second.trim().split(",").map { third ->
                third.trim().split(" ").let { fourth ->
                    Pair(fourth[0].toInt(), fourth[1])
                }
            }
        }
}

fun first(data: List<String>): Int {
    val possible = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )

    val formatted = formatValue(data)

    return formatted.foldIndexed(0) { index, acc, game ->
        game.find {
            it.first > possible[it.second]!!
        }?.let {
            acc
        } ?: run {
            acc + index + 1
        }
    }
}

fun second(data: List<String>): Int {
    val formatted = formatValue(data)

    return formatted.foldIndexed(0) { index, acc, game ->
        val blue = game.filter { it.second == "blue" }.maxOf { it.first }
        val red = game.filter { it.second == "red" }.maxOf { it.first }
        val green = game.filter { it.second == "green" }.maxOf { it.first }

        acc + (blue * red * green)
    }
}

fun main() {
//    val data = readFileExample(2)
    val data = readFile(2)
//    println(first(data))
    println(second(data))
}