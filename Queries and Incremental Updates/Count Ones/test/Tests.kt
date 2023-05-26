import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun sample1() {
        val sequence = "001001"
        val ones = countOnes(sequence)
        assertEquals(2, ones, "countOnes($sequence)")
    }

    @Test
    fun sample2() {
        val sequence = "11100101"
        val ones = countOnes(sequence)
        assertEquals(5, ones, "countOnes($sequence)")
    }
}
