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
        const val MAX_Q = 10_000_000
        private val rng = Random(239L)

        private fun genString(n: Int) = CharArray(n) { '0' + rng.nextInt(2) }.concatToString()

        private fun prependTestInfo(s: CharSequence, msg: String): String {
            return "[length(s) = ${s.length}] $msg"
        }

        private fun validateTest(s: CharSequence): Boolean {
            return s.all { it == '0' || it == '1' }
        }
    }

    @Test
    fun testEmpty() = runTimeout(1.seconds, "[Empty string]") {
        testSingle("", 5)
    }

    @Test
    fun manySmall() = runTimeout(5.seconds, "[Many small tests]") {
        for (len in 1..20) {
            repeat(20) {
                testSingle(genString(len), 100)
            }
        }
    }

    @Test
    fun randomSmall() = runTimeout(5.seconds, "[Small random tests]") {
        testRandom(20, MAX_Q)
    }

    @Test
    fun randomMediumInstances() = runTimeout(5.seconds, "[100 medium random tests]") {
        val tries = 100
        repeat(tries) {
            testRandom(100, MAX_Q / tries)
        }
    }


    @Test
    fun randomMedium() = runTimeout(5.seconds, "[Medium random tests]") {
        testRandom(200, MAX_Q / 2)
        testRandom(2000, MAX_Q / 2)
    }

    @Test
    fun randomLarge() = runTimeout(5.seconds, "[Large random tests]") {
        testRandom(20000, MAX_Q / 2)
        testRandom(200000, MAX_Q / 2)
    }

    @Test
    fun randomMax() = runTimeout(5.seconds, "[Almost maximum random test]") {
        testRandom(MAX_N, MAX_Q)
    }

    @Test
    fun testMax() = runTimeout(5.seconds, "[Maximum test]") {
        testSingle(genString(MAX_N), MAX_Q)
    }

    private fun testRandom(maxLen: Int, queries: Int) {
        testSingle(genString(rng.nextInt(maxLen / 2) + maxLen / 2), queries)
    }

    private fun testSingle(str: String, queries: Int) {
        if (!validateTest(str)) {
            throw AssertionError("test is incorrect")
        }
        val blocks = CountOnesWithUpdates(str)
        val s = str.toCharArray()
        var ones = s.count { it == '1' }
        repeat(queries) {
            val type = if (str.isEmpty()) {
                0
            } else {
                rng.nextInt(2)
            }
            when (type) {
                0 -> {
                    assertEquals(ones, blocks.countOnes()) {
                        prependTestInfo(str, "incorrect number of '1's")
                    }
                }

                1 -> {
                    val index = rng.nextInt(str.length)
                    blocks.flip(index)
                    if (s[index] == '1') {
                        ones--
                        s[index] = '0'
                    } else {
                        ones++
                        s[index] = '1'
                    }
                }
            }
        }
    }
}
