import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun sample1() {
        val a = intArrayOf(1, 2, 3)
        val correct = 6
        val result = maximumPairwiseProduct(a)
        assertEquals(result, correct, "maximumPairwiseProduct($a)")
    }

    @Test
    fun sample2() {
        val a = intArrayOf(7, 5, 14, 2, 8, 8, 10, 1, 2, 3)
        val correct = 140
        val result = maximumPairwiseProduct(a)
        assertEquals(result, correct, "maximumPairwiseProduct($a)")
    }

}
