fun main(){
    val puzzleInput = Utils.getTextContentOf("05.txt")
    val lines = puzzleInput.split(System.lineSeparator())
    println(lines.first().length)
    var coordinatesWithLines = mutableListOf<OceanCoordinate>()
fun mergeNewLinesIntoList(newCoordinates: List<OceanCoordinate>) {
    TODO("Not yet implemented")

}
    for (line in lines){
        val newCoordinates: List<OceanCoordinate> = parseCoordinatesFromLine(line)
        mergeNewLinesIntoList(newCoordinates)

    }
    val answer = coordinatesWithLines.filter { it.numberOfLines>1 }.size
    println("\n\n              Part one")
    println("   --------------------------")
    println("    number of points with at least two lines : $answer}")
    println("   --------------------------")

}


fun parseCoordinatesFromLine(line: String): List<OceanCoordinate> {
    val startEnd = line.split(" -> ")
    val startCoordinate = OceanCoordinate(startEnd[0].split(",")[0].toInt(), startEnd[0].split(",")[1].toInt())
    val endCoordinate = OceanCoordinate(startEnd[1].split(",")[0].toInt(), startEnd[1].split(",")[1].toInt())
    if(startCoordinate.x == endCoordinate.x && startCoordinate.y == endCoordinate.y){
        return listOf(startCoordinate)

    }
    if (startCoordinate.x == endCoordinate.x){

    }
    return emptyList()
}

class OceanCoordinate(val x: Int, val y: Int){
    var numberOfLines = 1

    fun increaseNumOfLines(){
        numberOfLines++
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OceanCoordinate

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }


}

