fun main() {
    fun init(stack: MutableList<Int>) {
        stack.add(0)
    }

    fun successor(stack: MutableList<Int>, k: Int, n: Int): Boolean {
        if(stack[k] < n) {
            stack[k]++
            return true
        }
        return false
    }

    fun valid(stack: MutableList<Int>, k: Int, caves: MutableSet<String>, conMatrix: Array<BooleanArray>, part: Int): Boolean {
        val visited = IntArray(conMatrix.size)
        for(i in 0 until k) {
            if(!conMatrix[stack[i]][stack[i+1]])
                return false
            if(caves.elementAt(stack[i]).lowercase() == caves.elementAt(stack[i]))
                if(caves.elementAt(stack[i]) != "start" && caves.elementAt(stack[i]) != "end") {
                    if(part == 1) {
                        if(visited[stack[i]] == 1)
                            return false
                    } else if(part == 2) {
                        if(visited[stack[i]] == 2)
                            return false
                    }
                    visited[stack[i]]++
                }
            if(part == 2) {
                if(visited.contains(2)) {
                    if(visited.count { it == 2 } > 1)
                        return false
                }
            }
        }
        if(caves.elementAt(stack[k]).lowercase() == caves.elementAt(stack[k]))
            if(caves.elementAt(stack[k]) != "start" && caves.elementAt(stack[k]) != "end")
                if(part == 1) {
                    if(visited[stack[k]] == 1)
                        return false
                } else if(part == 2) {
                    if(visited[stack[k]] == 2)
                        return false
                }
        return true
    }

    fun solution(stack: MutableList<Int>, k: Int, caves: MutableSet<String>): Boolean {
        if(caves.elementAt(stack[k]) == "end")
            return true
        return false
    }

    fun printPath(stack: MutableList<Int>, caves: MutableSet<String>) {
        for(c in stack) {
            print(caves.elementAt(c))
            if(c != stack.last())
                print("-")
        }
        println()
    }

    fun part1(input: List<String>): Int {
        val caves = mutableSetOf<String>()
        caves.add("start")
        for(line in input) {
            for(cave in line.split("-"))
                if(cave != "end")
                    caves.add(cave)
        }
        caves.add("end")
        val connectionMatrix = Array<BooleanArray>(caves.size) { it -> BooleanArray(caves.size) }
        for(line in input) {
            val connection = line.split("-")
            connectionMatrix[caves.indexOf(connection[0])][caves.indexOf(connection[1])] = true
            connectionMatrix[caves.indexOf(connection[1])][caves.indexOf(connection[0])] = true
        }
        var hasSuccessor: Boolean = false
        var isValid: Boolean = false
        var k = 1
        var paths = 0
        val stack = mutableListOf<Int>()
        init(stack)
        init(stack)
        while(k > 0) {
            do {
                hasSuccessor = successor(stack, k, caves.size-1)
                if(hasSuccessor)
                    isValid = valid(stack, k, caves, connectionMatrix, 1)
            } while(hasSuccessor && !isValid)
            if(hasSuccessor)
                if(solution(stack, k, caves)) {
//                    printPath(stack, caves)
                    paths++
                }
                else {
                    k++
                    init(stack)
                }
            else {
                stack.removeLast()
                k--
            }
        }
        return paths
    }

    fun part2(input: List<String>): Int {
        val caves = mutableSetOf<String>()
        caves.add("start")
        for(line in input) {
            for(cave in line.split("-"))
                if(cave != "end")
                    caves.add(cave)
        }
        caves.add("end")
        val connectionMatrix = Array<BooleanArray>(caves.size) { it -> BooleanArray(caves.size) }
        for(line in input) {
            val connection = line.split("-")
            connectionMatrix[caves.indexOf(connection[0])][caves.indexOf(connection[1])] = true
            connectionMatrix[caves.indexOf(connection[1])][caves.indexOf(connection[0])] = true
        }
        var hasSuccessor: Boolean = false
        var isValid: Boolean = false
        var k = 1
        var paths = 0
        val stack = mutableListOf<Int>()
        init(stack)
        init(stack)
        while(k > 0) {
            do {
                hasSuccessor = successor(stack, k, caves.size-1)
                if(hasSuccessor)
                    isValid = valid(stack, k, caves, connectionMatrix, 2)
            } while(hasSuccessor && !isValid)
            if(hasSuccessor)
                if(solution(stack, k, caves)) {
//                    printPath(stack, caves)
                    paths++
                }
                else {
                    k++
                    init(stack)
                }
            else {
                stack.removeLast()
                k--
            }
        }
        return paths
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day12_test")
    check(part1(testInput) == 19)
    check(part2(testInput) == 103)

    val input = readInput("Day12")
    println(part1(input))
    println(part2(input))
}
