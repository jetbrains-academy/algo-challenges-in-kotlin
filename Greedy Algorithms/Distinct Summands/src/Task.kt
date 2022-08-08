
fun distinctSummands(n: Int): List<Int> {
    val answer = mutableListOf<Int>()
    var left = n
    for (x in 1..n) {
        if (left - x <= x) {
            answer.add(left)
            break
        }
        left -= x
        answer.add(x)
    }
    return answer
}
