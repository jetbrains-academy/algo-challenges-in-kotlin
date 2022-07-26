import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Test {

    @Test
    fun testSample1() {
        checkSample(16, intArrayOf(1, 2, 3, 4, 5))
    }

    @Test
    fun testSample2() {
        checkSample(3, intArrayOf(1, 1))
    }

    @Test
    fun testSample3() {
        checkSample(4, intArrayOf(100, 1, 2))
    }

    private fun checkSample(expected: Long, v: IntArray) {
        assertEquals(
            expected,
            findMinimumChange(v),
            "Third sample ${v.contentToString()}",
        )
    }
}
