
class CountOnesWithUpdatesImpl(seq: CharSequence) : CountOnesWithUpdates {
    private val seq = CharArray(seq.length) { seq[it] }
    private var ones = seq.count { it == '1' }
    override fun countOnes() : Int {
        return ones
    }

    override fun flip(index: Int) {
        if (seq[index] == '0') {
            seq[index] = '1'
            ones++
        } else {
            seq[index] = '0'
            ones--
        }
    }
}
