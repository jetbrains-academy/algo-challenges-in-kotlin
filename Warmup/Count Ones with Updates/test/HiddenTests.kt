import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import java.lang.AssertionError
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class HiddenTests {
    companion object {
        const val MAX_N = 1_000_000
        const val MAX_Q = 10_000_000
        private val rng = Random(239L)

        private fun genString(n: Int) = (1..n).map {
            if (rng.nextBoolean()) '1' else '0'
        }.joinToString("")

        private fun prependTestInfo(s: CharSequence, msg: String): String {
            return "[length(s) = ${s.length}] $msg"
        }

        private fun validateTest(s: CharSequence): Boolean {
            return s.all { it == '0' || it == '1' } && s.isNotEmpty()
        }
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun randomSmall() {
        testRandom(20, MAX_Q)
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun randomMediumInstances() {
        val tries = 100
        repeat(tries) {
            testRandom(100, MAX_Q / tries)
        }
    }


    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun randomMedium() {
        testRandom(200, MAX_Q / 2)
        testRandom(2000, MAX_Q / 2)
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun randomLarge() {
        testRandom(20000, MAX_Q / 2)
        testRandom(200000, MAX_Q / 2)
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun randomMax() {
        testRandom(MAX_N, MAX_Q);
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun testMax() {
        testSingle(genString(MAX_N), MAX_Q)
    }

    private fun testRandom(maxLen: Int, queries: Int) {
        testSingle(genString(rng.nextInt(maxLen / 2) + maxLen / 2), queries)
    }

    private fun testSingle(str: String, queries: Int) {
        if (!validateTest(str)) {
            throw AssertionError("test is incorrect")
        }
        val blocks = createInstance(str)
        val s = str.toCharArray()
        var ones = s.count { it == '1' }
        repeat(queries) {
            when (rng.nextInt(2)) {
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
