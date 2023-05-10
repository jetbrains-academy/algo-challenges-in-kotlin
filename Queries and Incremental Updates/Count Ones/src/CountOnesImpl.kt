
class CountOnesImpl(private val seq: CharSequence) : CountOnes {
    override fun countOnes(): Int {
        var count = 0
        for (digit in seq) {
            if (digit == '1') {
                count += 1
            }
        }
        return count
    }
}
