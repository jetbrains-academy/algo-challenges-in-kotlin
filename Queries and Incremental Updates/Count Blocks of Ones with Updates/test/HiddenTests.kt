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
    }

    @Test
    fun testEmpty() = runTimeout(1.seconds, "Empty string") {
        testSingle("", 10)
    }

    @Test
    fun randomSmall() = runTimeout(5.seconds, "Random small test, $MAX_Q queries") {
        testRandom(20, MAX_Q)
    }

    @Test
    fun randomMediumInstances() = runTimeout(5.seconds, "100 Random medium tests, total $MAX_Q queries") {
        val tries = 100
        repeat(tries) {
            testRandom(100, MAX_Q / tries)
        }
    }


    @Test
    fun randomMedium() = runTimeout(5.seconds, "Random tests of ~200 and ~2000 length, total $MAX_Q queries") {
        testRandom(200, MAX_Q / 2)
        testRandom(2000, MAX_Q / 2)
    }

    @Test
    fun randomLarge() = runTimeout(5.seconds, "Random tests of ~20k and ~200k length, total $MAX_Q queries") {
        testRandom(20000, MAX_Q / 2)
        testRandom(200000, MAX_Q / 2)
    }

    @Test
    fun randomMax() = runTimeout(5.seconds, "Random test of ~$MAX_N length, $MAX_Q queries") {
        testRandom(MAX_N, MAX_Q)
    }

    @Test
    fun testMax() = runTimeout(5.seconds, "Random test of $MAX_N length, $MAX_Q queries") {
        testSingle(genString(MAX_N), MAX_Q)
    }

    private fun testRandom(maxLen: Int, queries: Int) {
        testSingle(genString(rng.nextInt(maxLen / 2) + maxLen / 2), queries)
    }

    private fun testSingle(str: String, queries: Int) {
        if (!validateTest(str)) {
            throw AssertionError("test is incorrect")
        }
        val blocks = CountBlocksOfOnesWithUpdates(str)
        val s = str.toCharArray()
        var onesCount = s.count { it == '1' }
        var blocksCount = countBlocks(str)
        repeat(queries) {
            var type = rng.nextInt(3)
            while (str.isEmpty() && type == 1) {
                type = rng.nextInt(3)
            }
            when (type) {
                0 -> {
                    assertEquals(onesCount, blocks.countOnes()) {
                        prependTestInfo(str, "incorrect number of '1's")
                    }
                }

                1 -> {
                    val index = rng.nextInt(str.length)
                    blocks.flip(index)
                    if (s[index] == '1') {
                        if (index == 0 || s[index - 1] == '0') {
                            blocksCount--
                        }
                        if (index + 1 < s.size && s[index + 1] == '1') {
                            blocksCount++
                        }
                        onesCount--
                        s[index] = '0'
                    } else {
                        if (index == 0 || s[index - 1] == '0') {
                            blocksCount++
                        }
                        if (index + 1 < s.size && s[index + 1] == '1') {
                            blocksCount--
                        }
                        onesCount++
                        s[index] = '1'
                    }
                }

                2 -> {
                    assertEquals(blocksCount, blocks.countBlocksOfOnes()) {
                        prependTestInfo(str, "incorrect number of blocks")
                    }
                }
            }
        }
    }
}
