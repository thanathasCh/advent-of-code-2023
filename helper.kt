import java.io.File

 fun readFile(questionNo: Int): List<String> =
    File("q$questionNo/input").readLines()

fun readFileExample(questionNo: Int): List<String> =
    File("q$questionNo/example").readLines()