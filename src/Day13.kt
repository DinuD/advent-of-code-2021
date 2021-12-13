import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        var i = 0
        val dots = mutableSetOf<Pair<Int, Int>>()
        while(input[i] != "") {
            val coords = input[i].split(",")
            dots.add(Pair(coords[0].toInt(), coords[1].toInt()))
            i++
        }
        i++
        while(i <= input.lastIndex) {
            val match = Regex("fold along (x|y)=(\\d+)").find(input[i])!!
            val tempDots = mutableListOf<Pair<Int, Int>>()
            if(match.groupValues[1] == "x") {
                for(d in dots) {
                    if(d.first > match.groupValues[2].toInt()) {
                        tempDots.add(Pair(match.groupValues[2].toInt() - abs(d.first-match.groupValues[2].toInt()), d.second))
                    }
                }
                dots.removeIf { it.first > match.groupValues[2].toInt() }
                dots.addAll(tempDots)
            } else if(match.groupValues[1] == "y") {
                for(d in dots) {
                    if(d.second > match.groupValues[2].toInt()) {
                        tempDots.add(Pair(d.first, match.groupValues[2].toInt() - abs(d.second-match.groupValues[2].toInt())))
                    }
                }
                dots.removeIf { it.second > match.groupValues[2].toInt() }
                dots.addAll(tempDots)
            }
            i++
            break
        }
        return dots.size
    }

    fun part2(input: List<String>): String {
        var i = 0
        val dots = mutableSetOf<Pair<Int, Int>>()
        while(input[i] != "") {
            val coords = input[i].split(",")
            dots.add(Pair(coords[0].toInt(), coords[1].toInt()))
            i++
        }
        i++
        while(i <= input.lastIndex) {
            val match = Regex("fold along (x|y)=(\\d+)").find(input[i])!!
            val tempDots = mutableListOf<Pair<Int, Int>>()
            if(match.groupValues[1] == "x") {
                for(d in dots) {
                    if(d.first > match.groupValues[2].toInt()) {
                        tempDots.add(Pair(match.groupValues[2].toInt() - abs(d.first-match.groupValues[2].toInt()), d.second))
                    }
                }
                dots.removeIf { it.first > match.groupValues[2].toInt() }
                dots.addAll(tempDots)
            } else if(match.groupValues[1] == "y") {
                for(d in dots) {
                    if(d.second > match.groupValues[2].toInt()) {
                        tempDots.add(Pair(d.first, match.groupValues[2].toInt() - abs(d.second-match.groupValues[2].toInt())))
                    }
                }
                dots.removeIf { it.second > match.groupValues[2].toInt() }
                dots.addAll(tempDots)
            }
            i++
        }
        var result = ""
        val maxX = dots.maxOf { it.first }
        val maxY = dots.maxOf { it.second }
        for(i in 0..maxY) {
            for(j in 0..maxX)
                if(dots.contains(Pair(j, i)))
                    result += "#"
                else
                    result += "."
            result += "\n"
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day13_test")
    check(part1(testInput) == 17)

    val input = readInput("Day13")
    println(part1(input))
    println(part2(input))
}
