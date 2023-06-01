import kotlin.math.max

fun maximumPairwiseProduct(a: IntArray): Int {
    val len = a.size
    var res = Int.MIN_VALUE
    for (i in 0 until len) {
        for (j in i + 1 until len) {
            res = max(res, a[i] * a[j])
        }
    }
    return res
}

