import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun sample() {
        val rsq = RangeSum(intArrayOf(1, 3, -2, 4, 2))
        assertEquals(1L, rsq.getSum(0, 1))
        assertEquals(8L, rsq.getSum(0, 5))
        assertEquals(0L, rsq.getSum(2, 2))
        assertEquals(-2L, rsq.getSum(2, 3))
        assertEquals(4L, rsq.getSum(2, 5))
        assertEquals(5, rsq.getSum(1, 4))
        assertEquals(0, rsq.getSum(0, 0))
        assertEquals(2, rsq.getSum(4, 5))
    }
}
