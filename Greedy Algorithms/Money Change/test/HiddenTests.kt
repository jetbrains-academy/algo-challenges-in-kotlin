import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.time.Duration.Companion.seconds

class HiddenTests {
    @Test
    fun test() = runTimeout(2.seconds, "All tests") {
        for (n in 0..1000) {
            val expected = n / 10 + (n % 10 / 5) + n % 5
            val actual = change(n)
            assertEquals(expected, actual) {
                "[n = $n]"
            }
        }
    }
}
