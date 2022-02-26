fun main(){
    val puzzleInput = Utils.getTextContentOf("02.txt")
    val commands: List<Command> = puzzleInput.split("\n").map {
            Command(it.split(" ")[0], it.split(" ")[1].trim().toInt())
    }
    var initialLocation= Location()
    var locationHistory= mutableListOf(initialLocation)


    fun partOne(){
        for(command in commands){
            val lastLocation = locationHistory.last()
            //println("current location: $lastLocation")
            when{
                "forward" in command.direction -> locationHistory.add(Location(lastLocation.depth,lastLocation.horizontalPosition+command.vlaue))
                "up" in command.direction -> locationHistory.add(Location(lastLocation.depth - command.vlaue, lastLocation.horizontalPosition))
                "down" in command.direction -> locationHistory.add(Location(lastLocation.depth + command.vlaue, lastLocation.horizontalPosition))
            }
        }
        val lastLocation = locationHistory.last()
        Utils.printPartOneAnswer("depth X horizontal position = ", lastLocation.depth * lastLocation.horizontalPosition)
    }
    fun partTwo(){
    initialLocation = Location()
    locationHistory = mutableListOf(initialLocation)
    var aim = 0
        for(command in commands){
            val lastLocation = locationHistory.last()
            when{
                "forward" in command.direction ->
                    locationHistory.add(Location(lastLocation.depth + (command.vlaue * aim), lastLocation.horizontalPosition + command.vlaue))
                "up" in command.direction -> aim -= command.vlaue
                "down" in command.direction -> aim += command.vlaue
            }
        }
        val lastLocation = locationHistory.last()
        Utils.printPartTwoAnswer("depth * horizontal position = ", lastLocation.depth * lastLocation.horizontalPosition )

    }

    partOne()
    partTwo()




}


data class Location(val depth: Int = 0,val horizontalPosition: Int = 0){
    override fun toString(): String {
        return "(↕ = $depth , ↔ = $horizontalPosition)"
    }
}
data class Command(val direction: String, val vlaue:Int){

}
