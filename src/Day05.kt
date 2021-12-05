import kotlin.math.abs

fun main() {
    fun containsPoint(x_point: Int, y_point: Int, line: List<Int>): Boolean {
        if(line[0] == line[2]) {
            if(x_point != line[0])
                return false
            val y1 = if(line[1]<line[3]) line[1] else line[3]
            val y2 = if(line[1]<line[3]) line[3] else line[1]
            for(y in y1..y2)
                if(y_point == y)
                    return true
            return false
        } else if(line[1] == line[3]) {
            if(y_point != line[1])
                return false
            val x1 = if(line[0]<line[2]) line[0] else line[2]
            val x2 = if(line[0]<line[2]) line[2] else line[0]
            for(x in x1..x2)
                if(x_point == x)
                    return true
            return false
        } else {
            var x = line[0]
            var y = line[1]
            if(line[0] < line[2]) {
                if(line[1] < line[3]) {
                    // down and to the right
                    while(x <= line[2] && y <= line[3]) {
                        if(x == x_point && y == y_point)
                            return true
                        x++
                        y++
                    }
                    return false
                } else {
                    // up and to the right
                    while(x <= line[2] && y >= line[3]) {
                        if(x == x_point && y == y_point)
                            return true
                        x++
                        y--
                    }
                    return false
                }
            } else {
                if(line[1] < line[3]) {
                    // down and to the left
                    while(x >= line[2] && y <= line[3]) {
                        if(x == x_point && y == y_point)
                            return true
                        x--
                        y++
                    }
                    return false
                } else {
                    // up and to the left
                    while(x >= line[2] && y >= line[3]) {
                        if(x == x_point && y == y_point)
                            return true
                        x--
                        y--
                    }
                    return false
                }
            }
        }
    }

    fun part1(input: List<String>): Int {
        val lines = mutableListOf<List<Int>>()
        var collisions = mutableSetOf<List<Int>>()
        for(l in input) {
            lines.add(l.split(" -> ", ",").map { it.toInt() })
            if(lines.last()[0] != lines.last()[2] && lines.last()[1] != lines.last()[3])
                continue
            for(line in lines) {
                if(line == lines.last())
                    break
                if(line[0] == line[2]) {
                    val y1 = if(line[1]<line[3]) line[1] else line[3]
                    val y2 = if(line[1]<line[3]) line[3] else line[1]
                    for(y in y1..y2)
                        if(containsPoint(line[0], y, lines.last()))
                            collisions.add(listOf(line[0], y))
                } else if(line[1] == line[3]) {
                    val x1 = if(line[0]<line[2]) line[0] else line[2]
                    val x2 = if(line[0]<line[2]) line[2] else line[0]
                    for(x in x1..x2)
                        if(containsPoint(x, line[1], lines.last()))
                            collisions.add(listOf(x, line[1]))
                }
            }
        }
        return collisions.size
    }

    fun part2(input: List<String>): Int {
        val lines = mutableListOf<List<Int>>()
        val collisions = mutableSetOf<List<Int>>()
        for(l in input) {
            lines.add(l.split(" -> ", ",").map { it.toInt() })
            for(line in lines) {
                if(line == lines.last())
                    break
                if(line[0] == line[2]) {
                    val y1 = if(line[1]<line[3]) line[1] else line[3]
                    val y2 = if(line[1]<line[3]) line[3] else line[1]
                    for(y in y1..y2)
                        if(containsPoint(line[0], y, lines.last()))
                            collisions.add(listOf(line[0], y))
                } else if(line[1] == line[3]) {
                    val x1 = if(line[0]<line[2]) line[0] else line[2]
                    val x2 = if(line[0]<line[2]) line[2] else line[0]
                    for(x in x1..x2)
                        if(containsPoint(x, line[1], lines.last()))
                            collisions.add(listOf(x, line[1]))
                } else {
                    var x = line[0]
                    var y = line[1]
                    if(line[0] < line[2]) {
                        if(line[1] < line[3]) {
                            // down and to the right
                            while(x <= line[2] && y <= line[3]) {
                                if(containsPoint(x, y, lines.last()))
                                    collisions.add(listOf(x, y))
                                x++
                                y++
                            }
                        } else {
                            // up and to the right
                            while(x <= line[2] && y >= line[3]) {
                                if(containsPoint(x, y, lines.last()))
                                    collisions.add(listOf(x, y))
                                x++
                                y--
                            }
                        }
                    } else {
                        if(line[1] < line[3]) {
                            // down and to the left
                            while(x >= line[2] && y <= line[3]) {
                                if(containsPoint(x, y, lines.last()))
                                    collisions.add(listOf(x, y))
                                x--
                                y++
                            }
                        } else {
                            // up and to the left
                            while(x >= line[2] && y >= line[3]) {
                                if(containsPoint(x, y, lines.last()))
                                    collisions.add(listOf(x, y))
                                x--
                                y--
                            }
                        }
                    }
                }
            }
        }
        return collisions.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
