import java.io.File
import java.lang.IllegalArgumentException

class TxtFileReader private constructor() {
    companion object {
        fun getTextContentOf(fileName: String): String {
            return TxtFileReader::class.java.getResource(fileName)?.readText()!!

        }
    }

}

