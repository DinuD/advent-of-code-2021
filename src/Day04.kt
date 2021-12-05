fun main() {
    fun checkWon(board: MutableList<Int>, start: Int): Int {
        var wonRow: Boolean
        var wonCol: Boolean
        for(i in 0..4) {
            wonRow = true
            wonCol = true
            for(j in 0..4) {
                if(board[start+i*5+j] != -1)
                    wonRow = false
                if(board[start+j*5+i] != -1)
                    wonCol = false
            }
            if(wonRow || wonCol) {
                var score = 0
                for(cell in board.subList(start, start+25))
                    if(cell != -1)
                        score += cell
                return score
            }
        }
        return -1
    }

    fun part1(input: List<String>): Int {
        val drawnNumbers = input[0].split(",")
        var x = 2
        val boardCells = mutableListOf<Int>()
        while(x <= input.lastIndex) {
            for(i in 0..4) {
                val line = input[x+i].replace("^\\s".toRegex(), "")
                    .replace("  ", " ")
                    .split(" ")
                for(number in line)
                    boardCells.add(number.toInt())
            }
            x += 6
        }
        for(x in drawnNumbers) {
            var score: Int
            boardCells.replaceAll { e -> if(e == x.toInt()) -1 else e }
            for(boardIndex in 0 until boardCells.size/25) {
                score = checkWon(boardCells, boardIndex*25)
                if(score != -1)
                    return score * x.toInt()
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        val drawnNumbers = input[0].split(",")
        var x = 2
        val boardCells = mutableListOf<Int>()
        while(x <= input.lastIndex) {
            for(i in 0..4) {
                val line = input[x+i].replace("^\\s".toRegex(), "")
                    .replace("  ", " ")
                    .split(" ")
                for(number in line)
                    boardCells.add(number.toInt())
            }
            x += 6
        }
        val boards = IntArray(boardCells.size/25)
        var lastDrawn = 0
        var lastScore = 0
        for(x in drawnNumbers) {
            var score: Int
            boardCells.replaceAll { e -> if(e == x.toInt()) -1 else e }
            for(boardIndex in 0 until boardCells.size/25) {
                if(boards[boardIndex] == 1)
                    continue
                score = checkWon(boardCells, boardIndex*25)
                if(score != -1) {
                    boards[boardIndex] = 1
                    lastDrawn = x.toInt()
                    lastScore = score
                }
            }
            if(!boards.contains(0))
                break
        }
        return lastScore * lastDrawn
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
