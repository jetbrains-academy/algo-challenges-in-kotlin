import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun sample1() {
        val input = intArrayOf(3, 1, 3, 4, 2)
        assertEquals(9, minimumSumPath(input))
    }


    @Test
    fun sample2() {
        val input = intArrayOf(1, 2, 3, -2, -2, 5);
        assertEquals(4, minimumSumPath(input))
    }

}