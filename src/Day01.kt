fun main() {
    fun part1(input: List<String>): Int {
        var increases = 0
        for(i in 1..input.lastIndex) {
            if(input[i].toInt() > input[i-1].toInt())
                increases++
        }
        return increases
    }

    fun part2(input: List<String>): Int {
        var increases = 0
        var sum = input[0].toInt() + input[1].toInt() + input[2].toInt()
        for(i in 1..input.lastIndex-2) {
            if(input[i].toInt() + input[i+1].toInt() + input[i+2].toInt() > sum)
                increases++
            sum = input[i].toInt() + input[i+1].toInt() + input[i+2].toInt()
        }
        return increases
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
