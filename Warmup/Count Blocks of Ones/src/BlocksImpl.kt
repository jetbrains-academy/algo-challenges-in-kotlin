
class BlocksImpl(private val seq: CharSequence) : Blocks {
    override fun countOnes(): Int {
        return seq.count { it == '1' }
    }

    override fun countBlocksOfOnes(): Int {
        var elementRecently = false
        var blocksCount = 0
        for (c in seq) {
            val isOne = c == '1'
            if (isOne) {
                if (!elementRecently) {
                    blocksCount += 1
                }
            }
            elementRecently = isOne
        }
        return blocksCount
    }
}
