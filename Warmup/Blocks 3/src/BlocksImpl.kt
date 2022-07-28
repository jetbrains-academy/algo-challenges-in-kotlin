
class BlocksImpl(seq: CharSequence) : Blocks {
    companion object {
        private fun initBlocks(seq: CharSequence, element: Char): Int {
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
    }
    private val seq = CharArray(seq.length) { seq[it] }
    private var countZeros = seq.count { it == '0' }
    private var countBlocksOfZeros = initBlocks(seq, '0')
    private var countBlocksOfOnes = initBlocks(seq, '1')

    override fun countZeros() = countZeros
    override fun countOnes() = seq.size - countZeros
    override fun countBlocksOfZeros() = countBlocksOfZeros
    override fun countBlocksOfOnes() = countBlocksOfOnes

    private fun is0(position: Int): Int {
        return if (seq[position] == '0') {
            1
        } else {
            0
        }
    }

    private fun is01(position: Int): Int {
        return if ((position < 0 || seq[position] == '0') && seq[position + 1] == '1') {
            1
        } else {
            0
        }
    }

    private fun is10(position: Int): Int {
        return if ((position < 0 || seq[position] == '1') && seq[position + 1] == '0') {
            1
        } else {
            0
        }
    }

    override fun flip(position: Int) {
        countZeros -= is0(position)
        countBlocksOfZeros -= is10(position - 1) + is10(position)
        countBlocksOfOnes -= is01(position - 1) + is01(position)

        seq[position] = when (seq[position]) {
            '0' -> '1'
            else -> '0'
        }

        countBlocksOfZeros += is10(position - 1) + is10(position)
        countBlocksOfOnes += is01(position - 1) + is01(position)
        countZeros += is0(position)
    }
}
