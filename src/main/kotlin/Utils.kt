import kotlin.math.abs
import kotlin.math.log10

class Utils private constructor() {
    companion object {
        fun getTextContentOf(fileName: String): String {
            return Utils::class.java.getResource(fileName)?.readText()!!

        }

        private fun printAnswerPretty(prefix: String, answer: Int, part: String){
            val answerStringLength = ((log10(answer.toDouble())+1).toInt() + prefix.length)
            val numberOfSpaces: Int =((abs( 26 - answerStringLength))/2)+3
            val numberofSpaces2: Int = ((abs( 10 - answerStringLength))/2)+3
            val spaces = " ".repeat(abs(numberOfSpaces))
            val spaces2 = " ".repeat(numberofSpaces2)
            val spaces3= if(answerStringLength < 26) spaces else ""
            println("\n\n$spaces3$spaces2$part")
            println("$spaces--------------------------")
            println("   $spaces3$prefix$answer")
            println("$spaces--------------------------")
        }

        fun printPartOneAnswer(prefix: String, answer: Int){
            printAnswerPretty(prefix,answer,"*Part One*")
        }
        fun printPartTwoAnswer(prefix: String, answer: Int){
            printAnswerPretty(prefix,answer,"*Part Two*")
        }
    }


}

