import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {

    @Test
    fun testSample1() {
        checkSample(16, intArrayOf(1, 2, 3, 4, 5), "First sample")
    }

    @Test
    fun testSample2() {
        checkSample(3, intArrayOf(1, 1), "Second sample")
    }

    @Test
    fun testSample3() {
        checkSample(4, intArrayOf(100, 1, 2), "Third sample")
    }

    private fun checkSample(expected: Long, v: IntArray, message: String) {
        assertEquals(
            expected,
            findMinimumChange(v),
            "$message ${v.contentToString()}",
        )
    }
}
