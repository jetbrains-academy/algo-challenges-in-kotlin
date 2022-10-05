import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun sample1() {
        val actual = generateDerangements(2)
        val expected = listOf(intArrayOf(1, 0)).map { Derangement(it) }
        assertTrue(actual == expected) {
            "Expected $expected, found $actual"
        }
    }

    @Test
    fun sample2() {
        val actual = generateDerangements(3)
        val expected = listOf(intArrayOf(1, 2, 0), intArrayOf(2, 0, 1)).map { Derangement(it) }
        assertTrue(actual == expected) {
            "Expected $expected, found $actual"
        }
    }
}
