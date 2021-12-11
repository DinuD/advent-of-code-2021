fun main() {
    fun addEnergy(i: Int, j: Int, grid:MutableList<MutableList<Pair<Int, Boolean>>>) {
        if(i < 0 || i > grid.lastIndex)
            return
        if(j < 0 || j > grid[i].lastIndex)
            return
        if(i > 0) {
            grid[i-1][j] = Pair(grid[i-1][j].first+if(!grid[i-1][j].second)1 else 0, grid[i-1][j].second)
            if(j > 0) {
                grid[i-1][j-1] = Pair(grid[i-1][j-1].first+if(!grid[i-1][j-1].second)1 else 0, grid[i-1][j-1].second)
                grid[i][j-1] = Pair(grid[i][j-1].first+if(!grid[i][j-1].second)1 else 0, grid[i][j-1].second)
            }
            if(j < grid[i].lastIndex) {
                grid[i-1][j+1] = Pair(grid[i-1][j+1].first+if(!grid[i-1][j+1].second)1 else 0, grid[i-1][j+1].second)
                grid[i][j+1] = Pair(grid[i][j+1].first+if(!grid[i][j+1].second)1 else 0, grid[i][j+1].second)
            }
        }
        if(i < grid.lastIndex) {
            grid[i+1][j] = Pair(grid[i+1][j].first+if(!grid[i+1][j].second)1 else 0, grid[i+1][j].second)
            if(j > 0) {
                if(i == 0)
                    grid[i][j-1] = Pair(grid[i][j-1].first+if(!grid[i][j-1].second)1 else 0, grid[i][j-1].second)
                grid[i+1][j-1] = Pair(grid[i+1][j-1].first+if(!grid[i+1][j-1].second)1 else 0, grid[i+1][j-1].second)
            }
            if(j < grid[i].lastIndex) {
                if(i == 0)
                    grid[i][j+1] = Pair(grid[i][j+1].first+if(!grid[i][j+1].second)1 else 0, grid[i][j+1].second)
                grid[i+1][j+1] = Pair(grid[i+1][j+1].first+if(!grid[i+1][j+1].second)1 else 0, grid[i+1][j+1].second)
            }
        }
    }

    fun flashes(i: Int, j: Int, grid:MutableList<MutableList<Pair<Int, Boolean>>>): Int {
        if(i < 0 || i > grid.lastIndex)
            return 0
        if(j < 0 || j > grid[i].lastIndex)
            return 0
        if(grid[i][j].first > 9 && !grid[i][j].second) {
            grid[i][j] = Pair(0, true)
            addEnergy(i, j, grid)
        } else return 0
        return 1 + flashes(i, j-1, grid)+
                flashes(i, j+1, grid)+
                flashes(i-1, j-1, grid)+
                flashes(i-1, j, grid)+
                flashes(i-1, j+1, grid)+
                flashes(i+1, j-1, grid)+
                flashes(i+1, j, grid)+
                flashes(i+1, j+1, grid)
    }

    fun allFlashed(grid: MutableList<MutableList<Pair<Int, Boolean>>>): Boolean {
        for(i in grid.indices)
            for(j in grid[i].indices)
                if(grid[i][j].first != 0)
                    return false
        return true
    }

    fun part1(input: List<String>): Int {
        val grid = mutableListOf<MutableList<Pair<Int, Boolean>>>()
        for(line in input) {
            grid.add(mutableListOf())
            for(c in line)
                grid.last().add(Pair(Character.getNumericValue(c), false))
        }
        var noOfFlashes = 0
        for(step in 1..100) {
            for(line in grid)
                line.replaceAll { it -> Pair(it.first+1, false) }
            for(i in grid.indices)
                for(j in grid[i].indices)
                    noOfFlashes += flashes(i, j, grid)
        }
        return noOfFlashes
    }

    fun part2(input: List<String>): Int {
        val grid = mutableListOf<MutableList<Pair<Int, Boolean>>>()
        for(line in input) {
            grid.add(mutableListOf())
            for(c in line)
                grid.last().add(Pair(Character.getNumericValue(c), false))
        }
        var done = true
        var step = 1
        do {
            for(line in grid)
                line.replaceAll { it -> Pair(it.first+1, false) }
            for(i in grid.indices)
                for(j in grid[i].indices)
                    flashes(i, j, grid)
            if(allFlashed(grid))
                return step
            step++
        } while(true)
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")
    check(part1(testInput) == 1656)
    check(part2(testInput) == 195)

    val input = readInput("Day11")
    println(part1(input))
    println(part2(input))
}
