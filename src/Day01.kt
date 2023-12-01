val digitsDict = mapOf(
    "1" to 1,
    "2" to 2,
    "3" to 3,
    "4" to 4,
    "5" to 5,
    "6" to 6,
    "7" to 7,
    "8" to 8,
    "9" to 9
)

val lettersDict = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)

fun Map<String, Int>.reverse(): Map<String, Int> {
    return mapKeys { it.key.reversed() }
}

fun main() {

    fun CharSequence.extractFirstNumberUsing(dict: Map<String, Int>): Int? {
        val buff = StringBuilder()
        return firstNotNullOfOrNull { c ->
            if (c.isDigit()) {
                return c.digitToInt()
            }
            buff
                .append(c)
                .takeIf { it.isNotEmpty() }
                ?.let { buffer -> dict.entries.firstOrNull { it.key in buffer }?.value }
        }
    }

    fun extractDigits(input: String, dict: Map<String, Int>): List<Int> = buildList {
        // find first digit/letter from start
        input.extractFirstNumberUsing(dict)?.let(::add)
        // find first digit/letter from end
        input.reversed().extractFirstNumberUsing(dict.reverse())?.let(::add)
    }

    fun sumOfCalibrationValues(input: List<String>, dict: Map<String, Int>) = input.sumOf {
        val list = extractDigits(it, dict)
        val first = list.first()
        val last = list.last()
        first * 10 + last
    }

    fun part1(input: List<String>): Int {
        return sumOfCalibrationValues(input, digitsDict)
    }

    fun part2(input: List<String>): Int {
        return sumOfCalibrationValues(input, digitsDict + lettersDict)
    }

    // test if implementation meets criteria from the description, like:
    val part1Res = part1(readInput("Day01_test1"))
    check(part1Res == 142)
    val part2Res = part2(readInput("Day01_test2"))
    check(part2Res == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
