import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun sample1() {
        val expectedLength = 1
        val found = findMinimumOperations(1)
        assertEquals(expectedLength, found.size)
        assertEquals(1, found[0])
    }

    @Test
    fun sample2() {
        val expectedLength = 15
        val n = 96234
        val found = findMinimumOperations(n)
        assertEquals(expectedLength, found.size)
        assertTrue(found.first() == 1)
        assertTrue(found.last() == n)
        for (i in 1 until found.size) {
            val a = found[i - 1]
            val b = found[i]
            assertTrue(a + 1 == b || a * 2 == b || a * 3 == b)
        }
    }
}
