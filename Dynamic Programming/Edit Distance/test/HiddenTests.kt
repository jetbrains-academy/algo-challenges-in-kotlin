import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import java.lang.AssertionError
import java.util.concurrent.TimeUnit
import kotlin.math.min

class HiddenTests {
    companion object {
        private const val MAX_LENGTH = 2000

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
    }

    private fun check(first: String, second: String) {
        if (!validate(first) || !validate(second)) {
            throw AssertionError("test case is incorrect")
        }
        assertEquals(computeAnswer(first, second), findEditDistance(first, second)) {
            "edit distance is wrong"
        }
    }

    @Test
    @Timeout(value = 30, unit = TimeUnit.SECONDS)
    fun allTestsUpTo10() {
        val strings = mutableListOf<String>()
        for (len in 0..10) {
            for (mask in 0 until (1 shl len)) {
                strings.add(mask.toString(2)
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
                check(first, second)
            }
        }
        println("$testsRun tests run")
    }

}