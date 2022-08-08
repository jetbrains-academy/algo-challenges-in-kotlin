
class BlocksImpl(private val seq: CharSequence) : Blocks {
    override fun countOnes(): Int {
        return seq.count { it == '1' }
    }
}
