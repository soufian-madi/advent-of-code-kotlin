fun main() {
    val puzzleInput = TxtFileReader.getTextContentOf("03.txt")
    val lines = puzzleInput.split("\n")

    fun partOne() {
        val gammaRate: String = calculateGamaRate(lines)
        val epsilonRate: String = gammaRate.replace('0', 'z').replace('1', '0').replace('z', '1')
        println(gammaRate)
        println(epsilonRate)
        println("\n\n          Part one")
        println("   ----------------------")
        println("Power consumption is " + gammaRate.toInt(2) * epsilonRate.toInt(2))
        println("   ----------------------")
    }

    partOne()
}


private fun calculateGamaRate(lines: List<String>): String {
    var gamaRate = ""
    for (i in 0 until lines[0].length) {
        var zerosCount = 0
        var onesCount = 0
        for (line in lines) {
            if (line[i] == '0')
                zerosCount++
            else if (line[i] == '1')
                onesCount++
        }
        gamaRate += (if (zerosCount > onesCount) "0" else "1")
    }
    return gamaRate
}

private fun convertStringsToInts(lines: List<String>): List<Int> {
    return lines.map {
        it.toInt(2)
    }

}