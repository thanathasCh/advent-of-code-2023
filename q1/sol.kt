package q1

import java.lang.Exception
import readFile

fun first(input: List<String>): Int {
    val answer = input.sumOf {
        val first = it.first { it in '0'..'9' }.toString()
        val second = it.last { it in '0'..'9' }.toString()

        (first + second).toInt()
    }

    return answer
}

fun second(input: List<String>): Int {
    val digits = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9",
        "zero" to "0",
        "0" to "0",
        "1" to "1",
        "2" to "2",
        "3" to "3",
        "4" to "4",
        "5" to "5",
        "6" to "6",
        "7" to "7",
        "8" to "8",
        "9" to "9"
    )
    val regex = "(?=(\\d|one|two|three|four|five|six|seven|eight|nine))".toRegex()

    return input.sumOf {
        val matches = regex.findAll(it)
        println(it)
        (digits[matches.first().groups[1]?.value] + digits[matches.last().groups[1]?.value]).toInt()
    }
}

fun main() {
    val input = readFile(1)
//    val input = readFileExample(1)
//    println(first(input))
    println(second(input))
}