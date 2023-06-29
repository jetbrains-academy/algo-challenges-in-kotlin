import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class HiddenTests {
    companion object {
        private val random = Random(228)
    }
    @Test
    fun example1() = runTimeout(1.seconds, "Example 1") {
        val result = countInRange(intArrayOf(2, 3, 5, 8, 10), 3, 9)
        Assertions.assertEquals(result, 3)
    }

    @Test
    fun example2() = runTimeout(1.seconds, "Example 2") {
        val result = countInRange(intArrayOf(2, 3, 5, 8, 10), 1, 20)
        Assertions.assertEquals(result, 5)
    }

    @Test
    fun example3() = runTimeout(1.seconds, "Example 3") {
        val result = countInRange(intArrayOf(2, 3, 5, 8, 10), 6, 7)
        Assertions.assertEquals(result, 0)
    }

}
