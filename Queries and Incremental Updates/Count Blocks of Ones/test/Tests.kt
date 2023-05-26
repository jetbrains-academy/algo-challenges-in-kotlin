import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun testSample1() {
        val sequence = "001001"
        val blocks = countBlocksOfOnes(sequence)
        assertEquals(2, blocks, "countBlocksOfOnes($sequence)")
    }

    @Test
    fun testSample2() {
        val sequence = "11100101"
        val blocks = countBlocksOfOnes(sequence)
        assertEquals(3, blocks, "countBlocksOfOnes($sequence)")
    }
}
