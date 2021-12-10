fun main() {
    fun part1(input: List<String>): Int {
        val map = mutableListOf<MutableList<Int>>()
        for(line in input) {
            map.add(mutableListOf<Int>())
            for(c in line) {
                map.last().add(Character.getNumericValue(c))
            }
        }

        var risk = 0
        for(i in map.indices) {
            for(j in map[i].indices) {
                if(i == 0) {
                    if(j == 0) {
                        if(map[i][j] < map[i+1][j] && map[i][j] < map[i][j+1])
                            risk += 1+map[i][j]
                    } else if(j == map[i].lastIndex) {
                        if(map[i][j] < map[i+1][j] && map[i][j] < map[i][j-1])
                            risk += 1+map[i][j]
                    } else {
                        if(map[i][j] < map[i+1][j] && map[i][j] < map[i][j-1] && map[i][j] < map[i][j+1])
                            risk += 1+map[i][j]
                    }
                } else if(i == map.lastIndex) {
                    if(j == 0) {
                        if(map[i][j] < map[i-1][j] && map[i][j] < map[i][j+1])
                            risk += 1+map[i][j]
                    } else if(j == map[i].lastIndex) {
                        if(map[i][j] < map[i-1][j] && map[i][j] < map[i][j-1])
                            risk += 1+map[i][j]
                    } else {
                        if(map[i][j] < map[i-1][j] && map[i][j] < map[i][j-1] && map[i][j] < map[i][j+1])
                            risk += 1+map[i][j]
                    }
                } else {
                    if(j == 0) {
                        if(map[i][j] < map[i+1][j] && map[i][j] < map[i][j+1] && map[i][j] < map[i-1][j])
                            risk += 1+map[i][j]
                    } else if(j == map[i].lastIndex) {
                        if(map[i][j] < map[i+1][j] && map[i][j] < map[i][j-1] && map[i][j] < map[i-1][j])
                            risk += 1+map[i][j]
                    } else {
                        if(map[i][j] < map[i+1][j] && map[i][j] < map[i][j-1] && map[i][j] < map[i][j+1] && map[i][j] < map[i-1][j])
                            risk += 1+map[i][j]
                    }
                }
            }
        }
        return risk
    }

    fun part2(input: List<String>): Int {
        val map = mutableListOf<MutableList<Int>>()
        for(line in input) {
            map.add(mutableListOf<Int>())
            for(c in line) {
                map.last().add(Character.getNumericValue(c))
            }
        }

        val lowPoints = mutableListOf<Pair<Int, Int>>()
        for(i in map.indices) {
            for(j in map[i].indices) {
                if(i == 0) {
                    if(j == 0) {
                        if(map[i][j] < map[i+1][j] && map[i][j] < map[i][j+1])
                            lowPoints.add(Pair(i, j))
                    } else if(j == map[i].lastIndex) {
                        if(map[i][j] < map[i+1][j] && map[i][j] < map[i][j-1])
                            lowPoints.add(Pair(i, j))
                    } else {
                        if(map[i][j] < map[i+1][j] && map[i][j] < map[i][j-1] && map[i][j] < map[i][j+1])
                            lowPoints.add(Pair(i, j))
                    }
                } else if(i == map.lastIndex) {
                    if(j == 0) {
                        if(map[i][j] < map[i-1][j] && map[i][j] < map[i][j+1])
                            lowPoints.add(Pair(i, j))
                    } else if(j == map[i].lastIndex) {
                        if(map[i][j] < map[i-1][j] && map[i][j] < map[i][j-1])
                            lowPoints.add(Pair(i, j))
                    } else {
                        if(map[i][j] < map[i-1][j] && map[i][j] < map[i][j-1] && map[i][j] < map[i][j+1])
                            lowPoints.add(Pair(i, j))
                    }
                } else {
                    if(j == 0) {
                        if(map[i][j] < map[i+1][j] && map[i][j] < map[i][j+1] && map[i][j] < map[i-1][j])
                            lowPoints.add(Pair(i, j))
                    } else if(j == map[i].lastIndex) {
                        if(map[i][j] < map[i+1][j] && map[i][j] < map[i][j-1] && map[i][j] < map[i-1][j])
                            lowPoints.add(Pair(i, j))
                    } else {
                        if(map[i][j] < map[i+1][j] && map[i][j] < map[i][j-1] && map[i][j] < map[i][j+1] && map[i][j] < map[i-1][j])
                            lowPoints.add(Pair(i, j))
                    }
                }
            }
        }
        var size1 = -1
        var size2 = -1
        var size3 = -1
//        for(point in lowPoints) {
//            var size = 0
//            var interrupted = false
//            for(i in point.first..map.lastIndex) {
//                for(j in point.second..map[i].lastIndex) {
//                    interrupted = false
//                    if(map[i][j] == 9) {
//                        break
//                    }
//                    for(k in i-1 downTo point.first+1)
//                        if(map[k][j] == 9) {
//                            interrupted = true
//                            break
//                        }
//                    if(interrupted)
//                        break
//                    if(map[i][j] < 9 && map[i][j] >= map[point.first][point.second] && !interrupted)
//                        size++
//                }
//                for(j in point.second-1 downTo 0) {
//                    interrupted = false
//                    if(map[i][j] == 9) {
//                        break
//                    }
//                    for(k in i-1 downTo point.first+1)
//                        if(map[k][j] == 9) {
//                            interrupted = true
//                            break
//                        }
//                    if(interrupted)
//                        break
//                    if(map[i][j] < 9 && map[i][j] >= map[point.first][point.second] && !interrupted)
//                        size++
//                }
//
//            }
//            for(i in point.first-1 downTo 0) {
//                for(j in point.second..map[i].lastIndex) {
//                    interrupted = false
//                    if(map[i][j] == 9) {
//                        break
//                    }
//                    for(k in i+1 until point.first)
//                        if(map[k][j] == 9) {
//                            interrupted = true
//                            break
//                        }
//                    if(interrupted)
//                        break
//                    if(map[i][j] < 9 && map[i][j] >= map[point.first][point.second] && !interrupted)
//                        size++
//                }
//                for(j in point.second-1 downTo 0) {
//                    interrupted = false
//                    if(map[i][j] == 9) {
//                        break
//                    }
//                    for(k in i+1 until point.first)
//                        if(map[k][j] == 9) {
//                            interrupted = true
//                            break
//                        }
//                    if(interrupted)
//                        break
//                    if(map[i][j] < 9 && map[i][j] >= map[point.first][point.second] && !interrupted)
//                        size++
//                }
//
//            }
//            val size = numberOfConnectedPoints(point, map)
//            if(size > size1) {
//                size3 = size2
//                size2 = size1
//                size1 = size
//            } else if(size > size2) {
//                size3 = size2
//                size2 = size
//            } else if(size > size3)
//                size3 = size
//        }
//        return size1 * size2 * size3
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 15)
    println(part2(testInput))
    check(part2(testInput) == 1134)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
