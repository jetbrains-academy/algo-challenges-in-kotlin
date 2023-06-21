
fun findMaximumAmountOfGold(backpackCapacity: Int, weights: IntArray): Int {
    val canMake = BooleanArray(backpackCapacity + 1)
    canMake[0] = true
    for (bar in weights) {
        for (w in backpackCapacity downTo bar) {
            if (canMake[w - bar]) {
                canMake[w] = true
            }
        }
    }
    var answer = backpackCapacity
    while (!canMake[answer]) {
        answer--
    }
    return answer
}
