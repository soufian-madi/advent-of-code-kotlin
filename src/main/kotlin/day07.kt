import kotlin.math.abs

fun main(){
    val puzzleInput:String = Utils.getTextContentOf("07.txt")
    val numbers: List<Int> = parseNumbers(puzzleInput)
    fun partOne(){
    fun calculateDistanceSum(position : Int): Int{
        var sum = 0
        numbers.forEach { sum+= abs(it-position)}
        return sum
    }


    var shortestDistance:Int = Int.MAX_VALUE
    var result : Int = -1;

    for (i in numbers.first()..numbers.last() ) {
        val currentDistanceSum= calculateDistanceSum(i)
        if (currentDistanceSum < shortestDistance){
            shortestDistance = currentDistanceSum
            result = i
        }
    }
    Utils.printPartOneAnswer("Optimal location is ", shortestDistance)
    }

    partOne()
}



fun parseNumbers(numbersWithKomata: String): List<Int> {
    var strings : List<String> = numbersWithKomata.split(",")
    var result : List<Int> =strings.map { it.toInt()}
    return result.sorted();
}



