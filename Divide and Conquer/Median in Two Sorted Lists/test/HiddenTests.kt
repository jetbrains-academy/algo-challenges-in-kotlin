import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertTimeoutPreemptively
import java.time.Duration
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory


class HiddenTests {

    private fun <T : Comparable<T>> testSingle(a: List<T>, b: List<T>, expected: T) {
        val actual = getMedian(a, b)
        assertEquals(expected, actual) {
            "Wrong median when n = ${a.size}"
        }
    }

    private fun testAllBinary(n: Int) {
        val a = generateSequence { 0 }.take(n).toMutableList()
        for (onesA in 0..n) {
            val b = generateSequence { 0 }.take(n).toMutableList()
            for (onesB in 0..n) {
                if (onesB < n) {
                    val expected = when (onesA + onesB > n) {
                        true -> 1
                        else -> 0
                    }
                    testSingle(a, b, expected)
                    b[n - onesB - 1] = 1
                }
            }
            if (onesA < n) {
                a[n - onesA - 1] = 1
            }
        }
    }

    @Test
    fun testBinaryAllUpTo20() {
        assertTimeoutPreemptively(Duration.ofSeconds(1)) {
            for (n in 1..20) {
                testAllBinary(n)
            }
        }
    }

    @Test
    fun testBinaryUpTo500() {
        for (n in 30..1000 step 17) {
            assertTimeoutPreemptively(Duration.ofSeconds(1), "testAllBinary($n) exceeded 1000 ms timeout") {
                testAllBinary(n)
            }
        }
    }

    @TestFactory
    fun testRandom() {

    }
}
