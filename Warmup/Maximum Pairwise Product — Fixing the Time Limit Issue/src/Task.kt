fun maximumPairwiseProduct(a: IntArray): Long {
    var firstMax = Int.MIN_VALUE
    var secondMax = Int.MIN_VALUE
    for (x in a) {
        if (x > firstMax) {
            secondMax = firstMax
            firstMax = x
        } else if (x > secondMax) {
            secondMax = x
        }
    }
    return firstMax.toLong() * secondMax
}
