import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun testSample1() {
        val sequence = "001001"
        val blocks = createBlocksInstance(sequence)
        assertEquals(2, blocks.countOnes(), "countOnes($sequence)")
        assertEquals(2, blocks.countBlocksOfOnes(), "countBlocksOfOnes($sequence)")
    }

    @Test
    fun testSample2() {
        val sequence = "11100101"
        val blocks = createBlocksInstance(sequence)
        assertEquals(5, blocks.countOnes(), "countOnes($sequence)")
        assertEquals(3, blocks.countBlocksOfOnes(), "countBlocksOfOnes($sequence)")
    }
}
