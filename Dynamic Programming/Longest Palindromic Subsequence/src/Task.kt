import kotlin.math.max

private class PalindromicSubsequenceFinder(private val seq: CharSequence) {
    companion object {
        const val PLACEHOLDER = -1
    }

    val longest = Array(seq.length) { IntArray(seq.length) { PLACEHOLDER } }

    private fun calculateLongest(left: Int, right: Int): Int {
        if (left >= right) return 0
        if (left + 1 == right) return 1
        if (longest[left][right - 1] != PLACEHOLDER) {
            return longest[left][right - 1]
        }
        var res = max(calculateLongest(left + 1, right), calculateLongest(left, right - 1))
        if (seq[left] == seq[right - 1]) {
            res = max(res, calculateLongest(left + 1, right - 1) + 2)
        }
        longest[left][right - 1] = res
        return res
    }

    private fun getLongest(left: Int, right: Int, subSeq: Appendable) {
        if (left >= right) return
        if (left + 1 == right) {
            subSeq.append(seq[left])
            return
        }
        when (calculateLongest(left, right)) {
            calculateLongest(left + 1, right) -> {
                getLongest(left + 1, right, subSeq)
            }
            calculateLongest(left, right - 1) -> {
                getLongest(left, right - 1, subSeq)
            }
            else -> { // length == calculateLongest(left + 1, right - 1) + 2
                subSeq.append(seq[left])
                getLongest(left + 1, right - 1, subSeq)
                subSeq.append(seq[right - 1])
            }
        }
    }

    fun find(): CharSequence {
        val result = StringBuilder()
        getLongest(0, seq.length, result)
        return result
    }
}

fun findLongestPalindromicSubsequence(seq: CharSequence): CharSequence {
    return PalindromicSubsequenceFinder(seq).find()
}
