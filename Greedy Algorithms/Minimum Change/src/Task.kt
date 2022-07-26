

fun findMinimumChange(v: IntArray): Long {
    v.sort()
    var answer = 1L
    for (value in v) {
        if (value > answer + 1) {
            break
        }
        answer += value
    }
    return answer
}