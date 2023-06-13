import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun testSample() {
        val sequence = "001001"
        val blocks = CountOnesWithUpdates(sequence)
        assertEquals(2, blocks.countOnes(), "countOnes($sequence)")
        blocks.flip(0);
        assertEquals(3, blocks.countOnes())
        blocks.flip(1);
        assertEquals(4, blocks.countOnes())
        blocks.flip(2);
        assertEquals(3, blocks.countOnes())
    }
}
