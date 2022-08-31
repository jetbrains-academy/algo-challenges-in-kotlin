import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import java.util.concurrent.TimeUnit

class HiddenTests {
    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun testAllUpTo20() {
        for (n in 1..20) {
            testSingle(n)
        }
    }

    private fun testSingle(n: Int) {
        val actual = generateBinaryStrings(n)
        val expected = (0 until (1 shl n)).map {
            convertToBinary(n, it)
        }
        Assertions.assertTrue(actual == expected) {
            "Wrong answer for n = $n"
        }
    }

    private fun convertToBinary(n: Int, x: Int): String {
        return x.toString(2).padStart(n, '0')
    }
}
