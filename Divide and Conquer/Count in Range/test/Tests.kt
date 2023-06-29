import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun example1() {
        val result = countInRange(intArrayOf(2, 3, 5, 8, 10), 3, 9)
        assertEquals(result, 3)
    }

    @Test
    fun example2() {
        val result = countInRange(intArrayOf(2, 3, 5, 8, 10), 1, 20)
        assertEquals(result, 5)
    }

    @Test
    fun example3() {
        val result = countInRange(intArrayOf(2, 3, 5, 8, 10), 6, 7)
        assertEquals(result, 0)
    }
}
