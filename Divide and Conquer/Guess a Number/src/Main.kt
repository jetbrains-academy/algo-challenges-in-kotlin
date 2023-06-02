fun main() {
    val lower = 1
    val upper = 10
    guess(lower, upper)
}

fun guess(lower: Int, upper: Int) {
    val middle = (lower + upper) / 2
    val result = query(middle)
    if (result == '=') {
        return
    } else if (result == '>') {
        guess(lower, middle - 1)
    } else {
        guess(middle + 1, upper)
    }
}

val x = 4 // Change this value for testing
fun query(y: Int): Char {
    val res = if (y == x) {
        '='
    } else if (y < x) {
        '<'
    } else {
        '>'
    }
    println("Request: $y, response: $y $res $x")
    return res
}
