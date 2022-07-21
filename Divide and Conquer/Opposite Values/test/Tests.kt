import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun sampleTests() {
        val result = findAnyOccurrenceOf01("000101")
        assertTrue(result == 2 || result == 4)

        assertEquals(findAnyOccurrenceOf01("000111"), 2)
    }
}
