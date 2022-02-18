fun main() {
    val puzzleInput = TxtFileReader.getTextContentOf("03.txt")
    val lines = puzzleInput.split(System.lineSeparator())

    fun partOne() {
        val gammaRate: String = calculateGamaRate(lines)
        val epsilonRate: String = gammaRate.replace('0', 'z').replace('1', '0').replace('z', '1')
        println("\n\n          Part one")
        println("   ----------------------")
        println("Power consumption is " + gammaRate.toInt(2) * epsilonRate.toInt(2))
        println("   ----------------------")
    }

    fun partTwo(){
        val ratingCalculator = RatingCalculator(lines)
        val oxygenRating =ratingCalculator.calculateOxygenRating()
        val co2Rating = ratingCalculator.calculateCo2Rating()

        println("\n\n          Part two")
        println("   ----------------------")
        println(" life support rating is " + oxygenRating * co2Rating)
        println("   ----------------------")
    }

    partOne()
     partTwo()
}

class RatingCalculator(val lines: List<String>) {
    val oxygenBitCriteria = OxygenBitCriteria()
    val co2BitCriteria = OxygenBitCriteria.Co2BitCriteria()

    fun calculateOxygenRating(): Int {
        return calculateRating(oxygenBitCriteria)

    }

    fun calculateCo2Rating(): Int {
        return calculateRating(co2BitCriteria)
    }
    private fun calculateRating(bitCriteria: Bitcriteria): Int {
        var linesRemaining: List<String> = lines
        for (i in 0 until lines[0].length) {
            val bitColumn: List<Char> = linesRemaining.map { it[i] }
            val winningBit = bitCriteria.calculateBitToWin(bitColumn)
            linesRemaining = linesRemaining.filter { it[i] == winningBit }
            if (linesRemaining.size ==1){
                return linesRemaining.first().toInt(2)
            }
        }
        throw Exception("no Rating found!")
    }




}


private fun calculateGamaRate(lines: List<String>): String {
    var gamaRate = ""
    for (i in 0 until lines[0].length-1) {
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
typealias bit = Int
private fun convertStringsToInts(lines: List<String>): List<Int> {
    return lines.map {
        it.toInt(2)
    }
}
interface Bitcriteria{
    fun calculateBitToWin(bitColumn: List<Char> ): Char
}

class OxygenBitCriteria: Bitcriteria {
    override fun calculateBitToWin(bitColumn: List<Char>): Char {
        val onesCounter = bitColumn.filter { it =='1' }.size
        val zerosCounter = bitColumn.filter { it =='0' }.size
        return if (zerosCounter > onesCounter) '0' else '1'
    }

class Co2BitCriteria: Bitcriteria{
    override fun calculateBitToWin(bitColumn: List<Char>): Char {
        val onesCounter = bitColumn.filter { it =='1' }.size
        val zerosCounter = bitColumn.filter { it =='0' }.size
        return if (onesCounter >= zerosCounter) '0' else '1'
    }

}
}