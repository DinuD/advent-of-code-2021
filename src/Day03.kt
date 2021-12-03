import kotlin.math.pow

fun main() {
    fun binaryToDecimal(binary: String): Int {
        var result = 0
        for(c in binary.lastIndex downTo 0) {
            result += Character.getNumericValue(binary[c]) * 2.0.pow((binary.lastIndex - c).toDouble()).toInt()
        }
        return result
    }

    fun part1(input: List<String>): Int {
        val frequencyList = mutableListOf<Int>()
        for(c in input[0])
            frequencyList.add(0)
        for(line in input) {
            for(i in line.indices)
                if(Character.getNumericValue(line[i]) == 1)
                    frequencyList[i]++
        }
        var gammaRate = ""
        var epsilonRate = ""
        for(i in frequencyList) {
            if(i > input.size-i) {
                gammaRate += "1"
                epsilonRate += "0"
            } else {
                gammaRate += "0"
                epsilonRate += "1"
            }
        }
        return binaryToDecimal(gammaRate) * binaryToDecimal(epsilonRate)
    }

    fun part2(input: List<String>): Int {
        var oxygenRating = ""
        var co2Rating = ""
        // searching for the oxygen generator rating value
        var filteredList = input.toMutableList()
        for(i in input[0].indices) {
            var numberOf1s = 0
            for(line in filteredList)
                if(line[i] == '1')
                    numberOf1s++
            filteredList = if(numberOf1s >= filteredList.size-numberOf1s)
                filteredList.filter { it[i] == '1' } as MutableList<String>
            else
                filteredList.filter { it[i] == '0' } as MutableList<String>
            if(filteredList.size == 1) {
                oxygenRating = filteredList[0]
                break
            }
        }

        // searching for the C02 scrubber rating value
        filteredList = input.toMutableList()
        for(i in input[0].indices) {
            var numberOf1s = 0
            for(line in filteredList)
                if(line[i] == '1')
                    numberOf1s++
            filteredList = if(numberOf1s >= filteredList.size-numberOf1s)
                filteredList.filter { it[i] == '0' } as MutableList<String>
            else
                filteredList.filter { it[i] == '1' } as MutableList<String>
            if(filteredList.size == 1) {
                co2Rating = filteredList[0]
                break
            }
        }
        return binaryToDecimal(oxygenRating) * binaryToDecimal(co2Rating)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
