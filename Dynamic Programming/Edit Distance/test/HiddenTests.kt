import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.AssertionError
import kotlin.math.min
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class HiddenTests {
    companion object {
        private const val MAX_LENGTH = 2000

        private val rng = Random(239L)

        private fun computeAnswer(s: String, t: String): Int {
            val d = Array(s.length + 1) { IntArray(t.length + 1) }
            for (i in 0..s.length) {
                for (j in 0..t.length) {
                    if (i == 0 || j == 0) {
                        d[i][j] = i + j
                    } else {
                        d[i][j] = min(d[i - 1][j], d[i][j - 1]) + 1
                        d[i][j] = min(d[i][j], d[i - 1][j - 1] + if (s[i - 1] == t[j - 1]) 0 else 1)
                    }
                }
            }
            return d[s.length][t.length]
        }

        private fun validate(s: String): Boolean {
            return s.length <= MAX_LENGTH && s.all { it.isLowerCase() }
        }

        private fun randomLetter(alphabet: Int) = (rng.nextInt(alphabet) + 'a'.code).toChar()

        private fun randomString(length: Int, alphabet: Int): String {
            return (1..length)
                .map { randomLetter(alphabet) }
                .joinToString("")
        }
    }

    private fun singleTest(first: String, second: String, withTimeout: Boolean = true) {
        if (!validate(first) || !validate(second)) {
            throw AssertionError("test case is incorrect")
        }
        val actual = runIfTimeout(withTimeout, 2.seconds) {
            findEditDistance(first, second)
        }
        val expected = computeAnswer(first, second)
        assertEquals(expected, actual) {
            "edit distance is wrong"
        }
    }

    @Test
    fun allBinaryTestsUpTo9() = runTimeout(10.seconds) {
        val strings = mutableListOf<String>()
        for (len in 0..9) {
            for (mask in 0 until (1 shl len)) {
                strings.add(
                    mask.toString(2)
                        .padStart(len, '0')
                        .replace('0', 'a')
                        .replace('1', 'b')
                )
            }
        }
        var testsRun = 0
        for (first in strings) {
            for (second in strings) {
                testsRun += 1
                singleTest(first, second, withTimeout = false)
            }
        }
        println("$testsRun tests run")
    }

    @ParameterizedTest
    @ValueSource(ints = [26, 10, 1, 2, 3, 4, 5, 6, 7, 8])
    fun maxRandom(alphabet: Int) {
        val s = randomString(MAX_LENGTH, alphabet)
        val t = randomString(MAX_LENGTH, alphabet)
        singleTest(s, t)
    }

    @ParameterizedTest
    @ValueSource(ints = [26, 10, 1, 2, 3, 4, 5, 6, 7, 8])
    fun smallDistance(alphabet: Int) {
        val s = randomString(MAX_LENGTH / 2, alphabet)
        val t = s.toCharArray().toMutableList()
        val changes = rng.nextInt(1, 21)
        repeat(changes) {
            when (rng.nextInt(3)) {
                0 -> {
                    val pos = rng.nextInt(t.size)
                    t[pos] = randomLetter(alphabet)
                }

                1 -> {
                    val pos = rng.nextInt(t.size)
                    t.removeAt(pos)
                }

                2 -> {
                    val pos = rng.nextInt(t.size + 1)
                    t.add(pos, randomLetter(alphabet))
                }
            }
        }
        singleTest(s, t.joinToString(""))
    }

    @Test
    fun randomSmall() = runTimeout(10.seconds) {
        makeRandom(20, 20, 1000)
    }

    @Test
    fun randomMedium() = runTimeout(10.seconds) {
        makeRandom(100, 100, 200)
    }

    @Test
    fun randomLarge() = runTimeout(10.seconds) {
        makeRandom(1000, 1000, 2)
    }

    @Test
    fun randomSmallLarge() = runTimeout(10.seconds) {
        makeRandom(20, 1000, 100)
    }

    @Test
    fun randomSmallMedium() = runTimeout(10.seconds) {
        makeRandom(20, 200, 500)
    }

    @Test
    fun randomMediumLarge() = runTimeout(10.seconds) {
        makeRandom(1000, 200, 10)
    }

    @Test
    fun randomMax() = runTimeout(10.seconds) {
        makeRandom(2000, 2000, 1)
    }

    private fun makeRandom(maxLen1: Int, maxLen2: Int, count: Int) {
        for (alphabet in intArrayOf(26, 2, 3, 4, 6, 7, 9, 10, 15, 20)) {
            repeat(count) {
                val len1 = rng.nextInt(maxLen1 / 2, maxLen1 + 1)
                val len2 = rng.nextInt(maxLen2 / 2, maxLen2 + 1)
                val first = randomString(len1, alphabet)
                val second = randomString(len2, alphabet)
                if (rng.nextBoolean()) {
                    singleTest(first, second, withTimeout = false)
                } else {
                    singleTest(second, first, withTimeout = false)
                }
            }
        }
    }
}
