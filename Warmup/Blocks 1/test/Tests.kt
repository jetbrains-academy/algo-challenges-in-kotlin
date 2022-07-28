import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun testSample() {
        val sequence = "001001"
        val blocks = createBlocksInstance(sequence)
        assertEquals(2, blocks.countOnes(), "countOnes($sequence)")
        assertEquals(4, blocks.countZeros(), "countZeros($sequence)")
    }
}
