fun guess(lower: Int, upper: Int) {
    val middle = (lower + upper) / 2
    val result = query(middle)
    if (result == '=') {
        return
    } else if (result == '<') {
        guess(lower, middle - 1)
    } else {
        guess(middle + 1, upper)
    }
}

val x = 4 // Change this value for testing
fun query(y: Int): Char {
    if (y == x) {
        return '='
    } else if (y < x) {
        return '<'
    } else {
        return '>'
    }
}
