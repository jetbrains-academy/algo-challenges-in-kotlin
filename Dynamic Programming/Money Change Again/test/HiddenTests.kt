import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.util.Random
import kotlin.math.min
import kotlin.time.Duration.Companion.seconds

class HiddenTests {

    companion object {
        private const val MAX = 1000000;
        private val table = IntArray(MAX + 1) { Int.MAX_VALUE };

        init {
            table[0] = 0
            for (m in 1..MAX) {
                for (c in sequenceOf(1, 3, 4)) {
                    if (c <= m) {
                        table[m] = min(table[m], 1 + table[m - c])
                    }
                }
            }
        }
    }

    @Test
    fun testAllUpTo10000() = runTimeout(10.seconds, "All up to 10000") {
        for (n in 0..10000) {
            testSingle(n)
        }
    }

    private fun testSingle(n: Int) {
        val actual = runTimeout(2.seconds, { "'changeMoney($n)' couldn't finish in time" }) {
            changeMoney(n)
        }
        assertEquals(actual, table[n]) {
            "money = $n"
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [MAX, MAX - 1, MAX - 2, MAX - 3, MAX - 4, MAX - 5, MAX - 6, MAX - 7, MAX - 8, MAX - 9, MAX - 10, MAX - 11, MAX - 12])
    fun testBig(money: Int) {
        testSingle(money)
    }

    @Test
    fun testRandom() {
        val rng = Random(239)
        repeat(5) {
            val n = rng.nextInt(MAX - 10015) + 10001
            testSingle(n)
        }
    }
}
