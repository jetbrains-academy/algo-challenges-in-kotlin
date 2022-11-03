import kotlin.system.measureTimeMillis

fun linear(a: IntArray): Int {
    var count = 0
    for (x in a) {
        if (x > 0) {
            count++
        }
    }
    return count
}

fun quadratic(a: IntArray): Int {
    var count = 0
    for (i in a.indices) {
        for (j in a.indices.drop(i + 1)) {
            if (a[i] + a[j] > 0) {
                count++
            }
        }
    }
    return count
}

fun exponential(a: IntArray): Long {
    fun backtrack(index: Int, sum: Long): Long {
        if (index == a.size) {
            return when {
                sum > 0 -> 1
                else -> 0
            }
        }
        return backtrack(index + 1, sum) + backtrack(index + 1, sum + a[index])
    }
    return backtrack(0, 0L)
}


fun main() {
    val n = 30
    val a = IntArray(n) { 1 }
    val time = measureTimeMillis {
        println(exponential(a))
    }
    println("Took $time ms")
}
