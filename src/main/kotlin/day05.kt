import kotlin.math.max
import kotlin.math.min

fun main() {
    val puzzleInput = Utils.getTextContentOf("05.txt")
    val lines = puzzleInput.split(System.lineSeparator())
    var coordinatesWithLines = mutableListOf<OceanCoordinate>()


    fun mergeNewLinesIntoList(newCoordinates: List<OceanCoordinate>) {
        for (coordinate: OceanCoordinate in newCoordinates) {
            coordinatesWithLines.find { it == coordinate }?.apply { increaseNumOfLines() } ?: coordinatesWithLines.add(coordinate)
        }
    }
    fun partOne(){
        for (line in lines) {
            val newCoordinates: List<OceanCoordinate> = parseCoordinatesFromStraightLine(line)
            mergeNewLinesIntoList(newCoordinates)

        }
        val answer = coordinatesWithLines.filter { it.numberOfLines > 1 }.size
        Utils.printPartOneAnswer("points where at least two lines overlap: ", answer)
    }
    fun partTwo(){
        coordinatesWithLines = mutableListOf<OceanCoordinate>()
        for (line in lines) {
            val newCoordinates: List<OceanCoordinate> = parseCoordinatesFromLine(line)
            mergeNewLinesIntoList(newCoordinates)

        }
        val answer = coordinatesWithLines.filter { it.numberOfLines > 1 }.size
        Utils.printPartTwoAnswer("points where at least two lines overlap: ", answer)
    }
    partOne()
    partTwo()
}

//parses Coordinate from Horzontal and vertical lines only
fun parseCoordinatesFromStraightLine(line: String): List<OceanCoordinate> {
    val startEnd = line.split(" -> ")
    val startCoordinate = OceanCoordinate(startEnd[0].split(",")[0].toInt(), startEnd[0].split(",")[1].toInt())
    val endCoordinate = OceanCoordinate(startEnd[1].split(",")[0].toInt(), startEnd[1].split(",")[1].toInt())
    if (startCoordinate.x == endCoordinate.x) {
        val coordinates = mutableListOf<OceanCoordinate>()
        for (i in min(startCoordinate.y, endCoordinate.y)..max(startCoordinate.y, endCoordinate.y)) {
            coordinates.add(OceanCoordinate(startCoordinate.x, i))
        }
        return coordinates
    }
    if (startCoordinate.y == endCoordinate.y) {
        val coordinates = mutableListOf<OceanCoordinate>()
        for (i in min(startCoordinate.x, endCoordinate.x)..max(startCoordinate.x, endCoordinate.x)) {
            coordinates.add(OceanCoordinate(i, startCoordinate.y))
        }
        return coordinates
    }
    return emptyList()
}
//parses Coordinate from all lines (diagonal too)
fun parseCoordinatesFromLine(line: String): List<OceanCoordinate> {
    val startEnd = line.split(" -> ")
    val startCoordinate = OceanCoordinate(startEnd[0].split(",")[0].toInt(), startEnd[0].split(",")[1].toInt())
    val endCoordinate = OceanCoordinate(startEnd[1].split(",")[0].toInt(), startEnd[1].split(",")[1].toInt())
    val coordWithSmallerXValue = if (startCoordinate.x > endCoordinate.x) endCoordinate else startCoordinate
    val coordWithBiggerXValue = if (startCoordinate.x <= endCoordinate.x) endCoordinate else startCoordinate
    val coordinates = mutableListOf<OceanCoordinate>()
    if (startCoordinate.x == endCoordinate.x) {
        for (i in min(startCoordinate.y, endCoordinate.y)..max(startCoordinate.y, endCoordinate.y)) {
            coordinates.add(OceanCoordinate(startCoordinate.x, i))
        }
        return coordinates
    }
    if (startCoordinate.y == endCoordinate.y) {
        for (i in min(startCoordinate.x, endCoordinate.x)..max(startCoordinate.x, endCoordinate.x)) {
            coordinates.add(OceanCoordinate(i, startCoordinate.y))
        }
        return coordinates
    }
    val direction= if (coordWithSmallerXValue.y < coordWithBiggerXValue.y) 1 else -1
    for (i in coordWithSmallerXValue.x..coordWithBiggerXValue.x){
        coordinates.add(OceanCoordinate(i,coordWithSmallerXValue.y+((i-coordWithSmallerXValue.x)*direction)))
    }
    return coordinates
}

class OceanCoordinate(val x: Int, val y: Int) {
    var numberOfLines = 1

    fun increaseNumOfLines() {
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

    override fun toString(): String {
        return "OceanCoordinate(x=$x, y=$y, numberOfLines=$numberOfLines)"
    }


}

