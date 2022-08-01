
class BlocksImpl(private val seq: CharSequence) : Blocks {
    override fun countZeros(): Int {
        return seq.count { it == '0' }
    }

    override fun countOnes(): Int {
        return seq.count { it == '1' }
    }
}
