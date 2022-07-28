
class BlocksImpl(private val seq: CharSequence) : Blocks {
    private fun count(element: Char): Int {
        return seq.count { it == element }
    }

    private fun countBlocks(element: Char): Int {
        var elementRecently = false
        var blocksCount = 0
        for (c in seq) {
            if (c == element) {
                if (!elementRecently) {
                    blocksCount += 1
                }
            }
            elementRecently = (c == element)
        }
        return blocksCount
    }

    override fun countZeros() = count('0')
    override fun countOnes() = count('1')
    override fun countBlocksOfZeros() = countBlocks('0')
    override fun countBlocksOfOnes() = countBlocks('1')
}
