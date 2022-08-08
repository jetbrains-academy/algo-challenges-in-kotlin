import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import java.lang.AssertionError
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class HiddenTests {
    companion object {
        const val MAX_N = 1_000_000
        private val rng = Random(239L)

        private fun genString(n: Int) = (1..n).map {
            if (rng.nextBoolean()) '1' else '0'
        }.joinToString("")

        private fun prependTestInfo(s: CharSequence, msg: String): String {
            return if (s.length < 15) {
                "[s = $s] $msg"
            } else {
                "[length(s) = ${s.length}] $msg"
            }
        }

        private fun validateTest(s: CharSequence): Boolean {
            return s.all { it == '0' || it == '1' } && s.isNotEmpty()
        }
    }
    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun randomSmall() {
        testRandom(100000, 20)
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun randomMedium() {
        testRandom(1000, 200)
        testRandom(1000, 2000)
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun randomLarge() {
        testRandom(100, 20000)
        testRandom(20, 200000)
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun randomMax() {
        testRandom(10, MAX_N);
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun testMax() {
        for (test in 0 until 10) {
            testSingle(genString(MAX_N))
        }
    }

    private fun testRandom(testsCount: Int, maxLen: Int) {
        for (test in 0..testsCount) {
            val s = genString(rng.nextInt(maxLen / 2) + maxLen / 2)
            testSingle(s)
        }
    }

    private fun testSingle(s: String) {
        if (!validateTest(s)) {
            throw AssertionError("test is incorrect")
        }
        val blocks = createInstance(s)
        assertEquals(s.count { it == '1' }, blocks.countOnes()) {
            prependTestInfo(s, "incorrect number of '1's")
        }
    }
}
