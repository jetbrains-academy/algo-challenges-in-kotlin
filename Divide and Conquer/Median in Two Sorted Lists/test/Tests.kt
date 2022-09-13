import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun sample1() {
        val first = listOf(1, 2, 5)
        val second = listOf(3, 4, 6)
        val actual = getMedian(first, second)
        assertEquals(3, actual)
    }

    @Test
    fun sample2() {
        val first = listOf("a", "z", "zz")
        val second = listOf("a", "a", "zz")
        val actual = getMedian(first, second)
        assertEquals("a", actual)
    }
}
