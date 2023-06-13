import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun sample() {
        val sequence = "001001"
        val blocks = CountBlocksOfOnesWithUpdates(sequence)
        assertEquals(2, blocks.countOnes(), "countOnes($sequence)")
        assertEquals(2, blocks.countBlocksOfOnes(), "countBlocksOfOnes($sequence)")
        blocks.flip(3)
        assertEquals(3, blocks.countOnes())
        assertEquals(2, blocks.countBlocksOfOnes())
        blocks.flip(4)
        assertEquals(4, blocks.countOnes())
        assertEquals(1, blocks.countBlocksOfOnes())
    }
}
