import kotlin.math.abs
    private val puzzleInput:String = Utils.getTextContentOf("07.txt")
    private val numbers: List<Int> = parseNumbers(puzzleInput)

private fun main(){
    fun calculateFuelConsumption(position : Int): Int{
        var sum = 0
        numbers.forEach { sum = sum.plus(abs(it-position)) }
        return sum
    }
    fun calculateNonLinearFuelConsumption(position: Int): Int {
        var sum = 0
        numbers.forEach { sum += (abs(it - position) * (abs(it - position) + 1)) / 2 }
        return sum
    }

    fun partOne(){
    var shortestDistance:Int = Int.MAX_VALUE
    for (i in numbers.first()..numbers.last() ) {
        val currentDistanceSum= calculateFuelConsumption(i)
        if (currentDistanceSum < shortestDistance){
            shortestDistance = currentDistanceSum
        }
    }
    Utils.printPartOneAnswer("Optimal location is ", shortestDistance)
    }

    fun partTwo(){

        var shortestDistance:Int = Int.MAX_VALUE

        for (i in numbers.first()..numbers.last() ) {
            val currentDistanceSum= calculateNonLinearFuelConsumption(i)
            if (currentDistanceSum < shortestDistance){
                shortestDistance = currentDistanceSum
            }
        }
        Utils.printPartTwoAnswer("Optimal location is ", shortestDistance)
    }

    partOne()
    partTwo()
}



fun parseNumbers(numbersWithKomata: String): List<Int> {
    val strings : List<String> = numbersWithKomata.split(",")
    val result : List<Int> =strings.map { it.toInt()}
    return result.sorted();
}



