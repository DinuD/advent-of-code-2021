fun main() {
    fun part1(input: List<String>): Int {
        val fish = mutableListOf<Int>()
        for(f in input[0].split(",")) {
            fish.add(f.toInt())
        }
        for(day in 1..80) {
            val newFish = fish.count { it == 0 }
            fish.replaceAll { e -> if(e == 0) 6 else e-1}
            for(i in 1..newFish)
                fish.add(8)
        }
        return fish.size
    }

    fun part2(input: List<String>): Long {
        val fishNumbers = LongArray(9)
        for(f in input[0].split(",")) {
            fishNumbers[f.toInt()]++
        }
        for(day in 1..256) {
            val newFish = fishNumbers[0]
            for(i in 1..8) {
                fishNumbers[i-1] += fishNumbers[i]
                fishNumbers[i] = 0
            }
            fishNumbers[8] += newFish
            fishNumbers[6] += newFish
            fishNumbers[0] -= newFish
        }
        var sum: Long = 0
        for(f in fishNumbers)
            sum += f
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934)
    check(part2(testInput) == 26984457539)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
