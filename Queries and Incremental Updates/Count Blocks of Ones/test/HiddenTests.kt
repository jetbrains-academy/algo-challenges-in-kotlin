import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import java.lang.AssertionError
import java.util.concurrent.TimeUnit
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class HiddenTests {
    companion object {
        const val MAX_N = 1_000_000
        private val rng = Random(239L)

        private fun genString(n: Int) = CharArray(n) { '0' + rng.nextInt(2) }.concatToString()

        private fun validateTest(s: CharSequence): Boolean {
            return s.all { it == '0' || it == '1' }
        }

        private fun countBlocks(seq: CharSequence): Int {
            var last = '0'
            var ans = 0
            for (c in seq) {
                if (c == '1' && last == '0') {
                    ans++
                }
                last = c
            }
            return ans
        }

        private fun shorten(s: String): String {
            return if (s.length < 15) {
                "s = \"$s\""
            } else {
                "length(s) = ${s.length}"
            }
        }
    }

    @Test
    fun testEmpty() = runTimeout(1.seconds, "Empty string") {
        testSingle("")
    }

    @Test
    fun randomUpTo10() = runTimeout(5.seconds, "Random length <= 10") {
        for (len in 1..10) {
            repeat(10000) {
                testSingle(genString(len))
            }
        }
    }

    @Test
    fun randomSmall() = runTimeout(5.seconds, "Small random tests") {
        testRandom(100000, 20)
    }

    @Test
    fun randomMedium() = runTimeout(5.seconds, "Medium random tests") {
        testRandom(1000, 200)
        testRandom(1000, 2000)
    }

    @Test
    fun randomLarge() = runTimeout(5.seconds, "Large random tests") {
        testRandom(100, 20000)
        testRandom(20, 200000)
    }

    @Test
    fun randomMax() = runTimeout(5.seconds, "Almost maximum random tests") {
        testRandom(10, MAX_N)
    }

    @Test
    fun testMax() = runTimeout(5.seconds, "Maximum random tests") {
        for (test in 0 until 10) {
            testSingle(genString(MAX_N))
        }
    }

    private fun testRandom(testsCount: Int, maxLen: Int) {
        for (test in 0..testsCount) {
            testSingle(genString(rng.nextInt(maxLen / 2) + maxLen / 2))
        }
    }

    private fun testSingle(s: String) {
        if (!validateTest(s)) {
            throw AssertionError("test is incorrect")
        }
        val blocks = countBlocksOfOnes(s)
        assertEquals(countBlocks(s), blocks) {
            "${shorten(s)} incorrect number of blocks"
        }
    }
}
