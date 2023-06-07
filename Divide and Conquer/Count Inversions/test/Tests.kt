import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun sample1() {
        val list = listOf(3, 1, 3, 4, 2)
        val actual = findInversions(list)
        assertArrayEquals(intArrayOf(0, 1, 0, 0, 3), actual)
    }
}
