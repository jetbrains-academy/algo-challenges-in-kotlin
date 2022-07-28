

fun findMinimumChange(v: IntArray): Long {
    v.sort()
    var answer = 1L
    for (value in v) {
        if (value > answer) {
            break
        }
        answer += value
    }
    return answer
}