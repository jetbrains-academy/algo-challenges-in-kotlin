import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class Tests {

    @Test
    fun sample1() {
        val points = intArrayOf(1, 6, 11)
        val segments = arrayOf(0 to 5, 7 to 10)
        val expected = intArrayOf(1, 0, 0)
        val found = countSegmentsForEach(points, segments)
        assertArrayEquals(expected, found)
    }

    @Test
    fun sample2() {
        val points = intArrayOf(-100, 100, 0)
        val segments = arrayOf(-10 to 10)
        val expected = intArrayOf(0, 0, 1)
        val found = countSegmentsForEach(points, segments)
        assertArrayEquals(expected, found)
    }

    @Test
    fun sample3() {
        val points = intArrayOf(1, 6)
        val segments = arrayOf(0 to 5, -3 to 2, 7 to 10)
        val expected = intArrayOf(2, 0)
        val found = countSegmentsForEach(points, segments)
        assertArrayEquals(expected, found)
    }
}