import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {

    private fun check(n: Long, expectedSize: Int) {
        val actual = distinctSummands(n)
        assertTrue(actual.size >= expectedSize) {
            "[n = $n] expected list size = $expectedSize, found ${actual.size}"
        }
        assertTrue(actual.toCollection(mutableSetOf()).size == actual.size) {
            "[n = $n] numbers are not distinct"
        }
        assertEquals(n, actual.sum()) {
            "[n = $n] sum of summands equals to ${actual.sum()}, but expected $n"
        }
        assertTrue(actual.size == expectedSize) {
            "you found a solution better than we expected, please file a bug"
        }
    }

    @Test
    fun sample1() {
        check(8, 3)
    }

    @Test
    fun sample2() {
        check(6, 3)
    }

    @Test
    fun sample3() {
        check(2, 1)
    }
}
