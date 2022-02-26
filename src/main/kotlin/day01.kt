fun main() {
    val puzzleInput = Utils.getTextContentOf("01.txt")
    val measurements = puzzleInput.split("\n").map {
        it.trim().toInt()
    }.toIntArray()
    println("(PART ONE)\nNumber of times the depth measurement increased:")
    partOne(measurements)
    println("(PART TWO)\nNumber of times the window measurement increased:")
    partTwo(measurements)


}

private fun partTwo(measurements: IntArray) {
    var windowsOfThree = IntArray(measurements.size - 2)
    for (i in 2 until measurements.size) {
        windowsOfThree.set(i - 2, measurements[i] + measurements[i - 1] + measurements[i - 2])
    }
    partOne(windowsOfThree)
}

private fun partOne(measurements: IntArray) {
    var increasedMeasurementCounter = 0
    for (i in 1 until measurements.size) {
        if (measurements[i - 1] < measurements[i])
            increasedMeasurementCounter++
    }

    println(increasedMeasurementCounter)
}
