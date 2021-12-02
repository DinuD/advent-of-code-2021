fun main() {
    fun part1(input: List<String>): Int {
        var x = 0
        var y = 0
        var instruction: List<String>
        for(line in input) {
            instruction = line.split(" ")
            when (instruction[0]) {
                "forward" -> {
                    x += instruction[1].toInt()
                }
                "up" -> {
                    y -= instruction[1].toInt()
                }
                "down" -> {
                    y += instruction[1].toInt()
                }
            }
        }
        return x * y
    }

    fun part2(input: List<String>): Int {
        var x = 0
        var y = 0
        var z = 0
        var instruction: List<String>
        for(line in input) {
            instruction = line.split(" ")
            when (instruction[0]) {
                "forward" -> {
                    x += instruction[1].toInt()
                    y += z * instruction[1].toInt()
                }
                "up" -> {
                    z -= instruction[1].toInt()
                }
                "down" -> {
                    z += instruction[1].toInt()
                }
            }
        }
        return x * y
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
