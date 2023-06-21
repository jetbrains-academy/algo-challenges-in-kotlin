import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun sample1() {
        val expected = 9
        val found = findMaximumAmountOfGold(10, intArrayOf(1, 4, 8))
        assertEquals(expected, found)
    }
}
