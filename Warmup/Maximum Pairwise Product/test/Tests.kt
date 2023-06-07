import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.max
import kotlin.random.Random

class Tests {

    @Test
    fun sample1() {
        val a = intArrayOf(1, 2, 3)
        val correct = 6
        val result = maximumPairwiseProduct(a)
        assertEquals(correct, result, "maximumPairwiseProduct(${a.contentToString()})")
    }

    @Test
    fun sample2() {
        val a = intArrayOf(7, 5, 14, 2, 8, 8, 10, 1, 2, 3)
        val correct = 140
        val result = maximumPairwiseProduct(a)
        assertEquals(correct, result, "maximumPairwiseProduct(${a.contentToString()})")
    }

}
