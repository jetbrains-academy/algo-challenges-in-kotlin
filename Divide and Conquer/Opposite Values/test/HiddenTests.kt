import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import java.util.stream.Collectors
import java.util.stream.Stream
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class HiddenTests {
    companion object {
        private val rng = Random(87)
    }

    @Test
    fun testUpTo100() = runTimeout(1.seconds, "Random tests") {
        for (n in listOf(2, 10, 100, 15, 42, 30)) {
            testRandom(n)
        }
        for (test in 0 until 2000) {
            testRandom(rng.nextInt(98) + 2)
        }
    }

    @Test
    fun testTimeLimit() {
        val tests = Array(10) {
            val n = rng.nextInt(200000) + 200000
            val zeros = rng.nextInt(n - 1) + 1
            val sb = StringBuilder()
            repeat(zeros) { sb.append('0') }
            repeat(n - zeros) { sb.append('1') }
            sb
        }
        runTimeout(1.seconds, "[500k queries on 10 modifiable sequences]") {
            for (test in 0 until 500000) {
                val testId = rng.nextInt(tests.size)
                val str = tests[testId]
                val changePosition = rng.nextInt(str.length - 2) + 1
                val currentChar = str[changePosition]
                str.setCharAt(
                    changePosition,
                    when (currentChar) {
                        '0' -> '1'
                        else -> '0'
                    }
                )
                testCharSequence(str)
                str.setCharAt(changePosition, currentChar)
            }
        }
    }

    private fun testRandom(n: Int) {
        val str = "0" + generateRandomBits(n - 2) + "1"
        validateCharSequence(str)
        testCharSequence(str)
    }

    private fun generateRandomBits(n: Int): String {
        return Stream
            .generate { rng.nextInt(2).toString() }
            .limit(n.toLong())
            .collect(Collectors.joining())!!
    }

    private fun testCharSequence(str: CharSequence) {
        val result = findAnyOccurrenceOf01(str)
        assertTrue(
            result >= 0 && result + 1 < str.length,
            "Return value $result in [0, ${str.length - 1})"
        )
        assertTrue(
            str.substring(result, result + 2) == "01",
            "occurrence is '01'"
        )
    }

    private fun validateCharSequence(str: CharSequence) {
        when {
            str.length < 2 -> {
                fail("sequence length ${str.length} is less than 2")
            }
            !str.all { it == '0' || it == '1' } -> {
                fail("sequence doesn't consist of '0' and '1'")
            }
            !str.startsWith('0') -> {
                fail("sequence doesn't start with '0'")
            }
            !str.endsWith('1') -> {
                fail("sequence doesn't end with '1'")
            }
        }
    }
}
