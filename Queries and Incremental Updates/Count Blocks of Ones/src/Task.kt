
fun countBlocksOfOnes(seq: CharSequence): Int {
    var oneRecently = false
    var blocksCount = 0
    for (c in seq) {
        val isOne = c == '1'
        if (isOne) {
            if (!oneRecently) {
                blocksCount += 1
            }
        }
        oneRecently = isOne
    }
    return blocksCount
}
