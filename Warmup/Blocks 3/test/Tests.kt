import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun testSample() {
        val sequence = "001001"
        val blocks = createBlocksInstance(sequence)
        assertEquals(2, blocks.countOnes(), "countOnes($sequence)")
        assertEquals(4, blocks.countZeros(), "countZeros($sequence)")
        assertEquals(2, blocks.countBlocksOfOnes(), "countBlocksOfOnes($sequence)")
        assertEquals(2, blocks.countBlocksOfZeros(), "countBlocksOfZeros($sequence)")
        blocks.flip(2)
        assertEquals(1, blocks.countOnes(), "countOnes($sequence)")
        assertEquals(5, blocks.countZeros(), "countZeros($sequence)")
        assertEquals(1, blocks.countBlocksOfOnes(), "countBlocksOfOnes($sequence)")
        assertEquals(1, blocks.countBlocksOfZeros(), "countBlocksOfZeros($sequence)")
    }
}
