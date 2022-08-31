import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun sample() {
        val actual = generateBinaryStrings(2)
        val expected = listOf("00", "01", "10", "11")
        assertTrue(actual == expected) {
            "[length = 4] Expected $expected, found $actual"
        }
    }
}
