private val list = TxtFileReader.getTextContentOf("04.txt").split(System.lineSeparator() + System.lineSeparator())
fun main() {
    val puzzleInput = TxtFileReader.getTextContentOf("04.txt").split(System.lineSeparator() + System.lineSeparator())
    val magicNumbers = parseNumbersFrom(puzzleInput[0])
    val boards = parseBoardsFrom(
        puzzleInput.subList(fromIndex = 1, toIndex = puzzleInput.size - 1), magicNumbers
    )
    partOne(magicNumbers, boards)
    magicNumbers.forEach { it.marked = false }
    partTwo(magicNumbers, boards)
}

private fun partOne(magicNumbers: List<Number>, boards: List<BingoBoard>) {
    for (magicNumber in magicNumbers) {
        magicNumber.marked = true
        for (board in boards) {
            if (board.hasWon()) {
                printWinningBoard(board, magicNumber)
                return
            }
        }
    }
    println("Keine Gewinner")
}

private fun partTwo(magicNumbers: List<Number>, boards: List<BingoBoard>) {
    var notWinningBoards = boards
    for (magicNumber in magicNumbers) {
        magicNumber.marked = true
        val lastNotWinningBoard = notWinningBoards.last()
        notWinningBoards = notWinningBoards.filter { !it.hasWon() }.toList()
        if (notWinningBoards.isEmpty()) {
            printLoosingBoard(lastNotWinningBoard, magicNumber)
            return
        }
    }
    println("There are ${notWinningBoards.size} boards left")
}


fun parseBoardsFrom(boardDefinitions: List<String>, magicNumbers: List<Number>): List<BingoBoard> {
    val whitespaces = Regex("\\s+")
    return boardDefinitions.map {
        BingoBoard(it.split(whitespaces).filter { entry -> entry.isNotEmpty() }.map {
            magicNumbers.first { number -> number.value == it.toInt() }
        })
    }.toList()
}

fun printWinningBoard(board: BingoBoard, lastNumber: Number) {
    println("\n\n              Part one")
    println("   --------------------------")
    println("     Winner: Score ${board.getScore(lastNumber)}")
    println("   --------------------------")
}

fun printLoosingBoard(board: BingoBoard, lastNumber: Number) {
    println("\n\n              Part two")
    println("   --------------------------")
    println("Last Board Standing: Score ${board.getScore(lastNumber)}")
    println("   --------------------------")
}

fun parseNumbersFrom(numbersString: String): List<Number> {
    return numbersString.split(',').map { Number(it.toInt()) }.toList()
}

class Number(val value: Int, var marked: Boolean = false) {
    override fun toString(): String {
        return "Number(value=$value, marked=$marked)"
    }
}

class BingoBoard(val numbers: List<Number>) {
    fun getScore(lastNumber: Number): Int {
        val sum = numbers.filter { number -> !number.marked }.sumOf { it.value }
        return sum * lastNumber.value
    }

    fun hasWon(): Boolean {
        for (rowIndex in 0..4) {
            var markedInRow = 0
            var markedInColumn = 0
            for (columnIndex in 0..4) {
                if (numbers[(rowIndex * 5) + columnIndex].marked)
                    markedInRow++
                if (numbers[(columnIndex * 5) + rowIndex].marked)
                    markedInColumn++
            }
            if (markedInRow == 5 || markedInColumn == 5)
                return true
        }
        return false
    }

    override fun toString(): String {
        return "BingoBoard(numbers=$numbers)"
    }
}
