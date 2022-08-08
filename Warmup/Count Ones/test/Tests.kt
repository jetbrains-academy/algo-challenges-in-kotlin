import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun sample1() {
        val sequence = "001001"
        val blocks = createInstance(sequence)
        assertEquals(2, blocks.countOnes(), "countOnes($sequence)")
    }

    @Test
    fun sample2() {
        val sequence = "11100101"
        val blocks = createInstance(sequence)
        assertEquals(5, blocks.countOnes(), "countOnes($sequence)")
    }
}
