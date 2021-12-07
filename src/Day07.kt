import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val positions = input[0].split(",")
        var sum = 0
        var minCost = 999999999
        for(finalPos in positions) {
            sum = 0
            for(pos in positions) {
                sum += abs(pos.toInt() - finalPos.toInt())
            }
            if(sum < minCost)
                minCost = sum
        }
        return minCost
    }

    fun part2(input: List<String>): Int {
        val positions = input[0].split(",").map { it.toInt() }
        var sum = 0
        var minCost = 999999999
        for(finalPos in 0..positions.maxOf { it }) {
            sum = 0
            for(pos in positions) {
                for(i in 1..abs(pos-finalPos))
                    sum += i
            }
            if(sum < minCost)
                minCost = sum
        }
        return minCost
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
