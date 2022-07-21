
fun findAnyOccurrenceOf01(str: CharSequence): Int {
    var left = 0
    var right = str.length - 1
    while (left + 1 < right) {
        val mid = (left + right) ushr 1
        if (str[mid] == '1') {
            right = mid
        } else {
            left = mid
        }
    }
    return left
}
