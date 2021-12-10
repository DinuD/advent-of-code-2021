fun main() {
    fun part1(input: List<String>): Int {
        var score = 0
        for(line in input) {
            val stack = mutableListOf<Char>()
            for(c in line) {
                when(c) {
                    ')' -> if(stack.last() != '(') {
                        score += 3
                        break
                    } else stack.removeLast()

                    ']' -> if(stack.last() != '[') {
                        score += 57
                        break
                    } else stack.removeLast()

                    '}' -> if(stack.last() != '{') {
                        score += 1197
                        break
                    } else stack.removeLast()

                    '>' -> if(stack.last() != '<') {
                        score += 25137
                        break
                    } else stack.removeLast()

                    else -> stack.add(c)
                }
            }
        }
        return score
    }

    fun part2(input: List<String>): Long {
        val scores = mutableListOf<Long>()
        for(line in input) {
            var score: Long = 0
            val stack = mutableListOf<Char>()
            var next = false
            for(c in line) {
                when(c) {
                    ')' -> if(stack.last() != '(') {
                        next = true
                        break
                    } else stack.removeLast()

                    ']' -> if(stack.last() != '[') {
                        next = true
                        break
                    } else stack.removeLast()

                    '}' -> if(stack.last() != '{') {
                        next = true
                        break
                    } else stack.removeLast()

                    '>' -> if(stack.last() != '<') {
                        next = true
                        break
                    } else stack.removeLast()

                    else -> stack.add(c)
                }
            }
            if(next)
                continue
            if(stack.isNotEmpty())
                for(i in stack.lastIndex downTo 0)
                    when(stack[i]) {
                        '(' -> {
                            score *= 5
                            score += 1
                        }

                        '[' -> {
                            score *= 5
                            score += 2
                        }

                        '{' -> {
                            score *= 5
                            score += 3
                        }

                        '<' -> {
                            score *= 5
                            score += 4
                        }
                    }
            scores.add(score)
        }
        return scores.sorted()[scores.size/2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 26397)
//    check(part2(testInput) == 288957)

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}
