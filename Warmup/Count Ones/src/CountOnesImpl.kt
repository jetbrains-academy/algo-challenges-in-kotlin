
class CountOnesImpl(private val seq: CharSequence) : CountOnes {
    override fun countOnes(): Int {
        return seq.count { it == '1' }
    }
}
