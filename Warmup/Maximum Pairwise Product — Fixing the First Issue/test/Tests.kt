import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun sample1() {
        val a = intArrayOf(1, 2, 3)
        val correct = 6L
        val result = maximumPairwiseProduct(a)
        assertEquals(result, correct, "maximumPairwiseProduct(${a.contentToString()})")
    }

    @Test
    fun sample2() {
        val a = intArrayOf(7, 5, 14, 2, 8, 8, 10, 1, 2, 3)
        val correct = 140L
        val result = maximumPairwiseProduct(a)
        assertEquals(result, correct, "maximumPairwiseProduct(${a.contentToString()})")
    }

    @Test
    fun sample3() {
        val a = intArrayOf(1_000_000, 1_000_000)
        val correct = 1_000_000_000_000L
        val result = maximumPairwiseProduct(a)
        assertEquals(correct, result, "maximumPairwiseProduct(${a.contentToString()}")
    }

}
