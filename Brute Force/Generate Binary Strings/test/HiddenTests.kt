import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.time.Duration.Companion.seconds

class HiddenTests {
    @Test
    fun testAllUpTo20() {
        for (n in 0..20) {
            testSingle(n)
        }
    }

    private fun testSingle(n: Int) {
        val actual = runTimeout(1.seconds, "n = $n") {
            generateBinaryStrings(n)
        }
        val expected = (0 until (1 shl n)).map {
            convertToBinary(n, it)
        }
        Assertions.assertTrue(actual == expected) {
            "Wrong answer for n = $n"
        }
    }

    private fun convertToBinary(n: Int, x: Int): String {
        return when (n) {
            0 -> ""
            else -> x.toString(2).padStart(n, '0')
        }
    }
}
