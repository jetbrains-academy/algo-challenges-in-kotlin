import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.time.Duration.Companion.seconds

class HiddenTests {
    @Test
    fun testAllPairs() = runTimeout(2.seconds, "All tests") {
        for (a in 0 until 10) {
            for (b in 0 until 10) {
                testSingle(a, b)
            }
        }
    }

    private fun testSingle(a: Int, b: Int) {
        val correct = a + b
        val result = sumOfTwoDigits(a, b)
        assertEquals(result, correct, "countOnes($a, $b)")
    }
}
