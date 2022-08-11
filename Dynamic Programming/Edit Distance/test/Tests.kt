import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {

    @Test
    fun sample1() {
        check(3, "short", "ports")
    }

    @Test
    fun sample2() {
        check(5, "editing", "distance")
    }

    @Test
    fun sample3() {
        check(0, "ab", "ab")
    }

    private fun check(expected: Int, first: String, second: String) {
        assertEquals(expected, findEditDistance(first, second)) {
            "editDistance($first, $second) wrong"
        }
    }
}
