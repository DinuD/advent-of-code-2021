fun main() {
    fun part1(input: List<String>): Int {
        var appearances = 0
        for(line in input) {
            val note = line.split("|")
            val digits = note[0].split(" ")
            val output = note[1].split(" ")
            for(digit in output) {
                when(digit.length) {
                    2 -> appearances++
                    3 -> appearances++
                    4 -> appearances++
                    7 -> appearances++
                }
            }
        }
        return appearances
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for(line in input) {
            val note = line.split("|")
            val digits = note[0].split(" ").toMutableList()
            val output = note[1].split(" ").toMutableList()
            digits.remove("")
            output.remove("")
            val digitArray = Array<String>(10) { "" }
            digitArray[1] = digits.find { it.length == 2 } ?: ""
            digitArray[4] = digits.find { it.length == 4 } ?: ""
            digitArray[7] = digits.find { it.length == 3 } ?: ""
            digitArray[8] = digits.find { it.length == 7 } ?: ""

            var middle: Char = 'z'
            while(digitArray.contains("")) {
                for(d in digits.filter { digitArray.indexOf(it) == -1 }) {
                    if(digitArray.indexOf(d) != -1)
                        continue
                    for(c in digitArray[4])
                        if(!d.contains(c) && d != digitArray[4] && d.length == 6 && !digitArray[1].contains(c)) {
                            digitArray[0] = d
                            middle = c
                            continue
                        }
                    if(d.length == 6 && middle != 'z') {
                        for(c in digitArray[8])
                            if(!d.contains(c) && digitArray[1].contains(c)) {
                                digitArray[6] = d
                                continue
                            } else if(!d.contains(c) && d.contains(middle)) {
                                digitArray[9] = d
                                continue
                            }
                    }
                    if(d.length == 5) {
                        if(d.contains(digitArray[1][0]) && d.contains(digitArray[1][1])) {
                            digitArray[3] = d
                            continue
                        }
                        if(digitArray[0] != "") {
                            for(c in digitArray[4])
                                if(!digitArray[1].contains(c) && c != middle) {
                                    if(d.contains(c)) {
                                        digitArray[5] = d
                                        continue
                                    } else {
                                        digitArray[2] = d
                                        continue
                                    }
                                }
                        }
                    }
                }
            }
            for(i in 0..9)
                digitArray[i] = digitArray[i].toCharArray().sorted().joinToString("")
            for(i in output.indices)
                output[i] = output[i].toCharArray().sorted().joinToString("")
            var num = 0
            for(d in output)
                num = num*10 + digitArray.indexOf(d)
            sum += num
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
