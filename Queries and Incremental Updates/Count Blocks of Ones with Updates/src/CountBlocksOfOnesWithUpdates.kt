
class CountBlocksOfOnesWithUpdates(seq: CharSequence) {
    companion object {
        private fun isBlockStart(first: Char, second: Char): Boolean {
            return first == '0' && second == '1'
        }
    }

    private val seq = CharArray(seq.length) { seq[it] }
    private var ones = seq.count { it == '1' }
    private var blocks =
        (sequenceOf('0') + seq.asSequence()).zipWithNext().count { isBlockStart(it.first, it.second) }

    fun countOnes(): Int {
        return ones
    }

    fun countBlocksOfOnes(): Int {
        return blocks
    }

    fun flip(index: Int) {
        val prev = if (index == 0) '0' else seq[index - 1]
        val next = if (index + 1 < seq.size) seq[index + 1] else '0'
        if (isBlockStart(prev, seq[index])) {
            blocks--
        }
        if (isBlockStart(seq[index], next)) {
            blocks--
        }
        if (seq[index] == '0') {
            ones++
        } else {
            ones--
        }
        seq[index] = if (seq[index] == '0') '1' else '0'
        if (isBlockStart(prev, seq[index])) {
            blocks++
        }
        if (isBlockStart(seq[index], next)) {
            blocks++
        }
    }
}
