import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.max
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class HiddenTests {

    companion object {
        private const val MAX_N = 100
        private const val MAX_VAL = 1000000
    }

    @Test
    fun sample1() {
        val a = intArrayOf(1, 2, 3)
        val correct = 6L
        val result = maximumPairwiseProduct(a).toLong()
        assertEquals(result, correct, "maximumPairwiseProduct(${a.contentToString()})")
    }

    @Test
    fun sample2() {
        val a = intArrayOf(7, 5, 14, 2, 8, 8, 10, 1, 2, 3)
        val correct = 140L
        val result = maximumPairwiseProduct(a).toLong()
        assertEquals(result, correct, "maximumPairwiseProduct(${a.contentToString()})")
    }

    @Test
    fun sample3() {
        val a = intArrayOf(1_000_000, 1_000_000)
        val correct = 1_000_000_000_000L
        val result = maximumPairwiseProduct(a).toLong()
        assertEquals(correct, result, "maximumPairwiseProduct(${a.contentToString()}")
    }

    @Test
    fun random_tests() = runTimeout(2.seconds, "random_tests") {
        val TESTS_NUMBER = 100
        val rand = Random(239)
        for (i in 1..TESTS_NUMBER) {
            val n = rand.nextInt(2, MAX_N)
            val a = IntArray(n) { rand.nextInt(0, MAX_VAL) }
            val result = maximumPairwiseProduct(a).toLong()
            val correct = calcAnswer(a)
            assertEquals(correct, result, "Random test $i")
        }
    }

    @Test
    fun maximal_tests() = runTimeout(1.seconds, "maximal_test") {
        val n = MAX_N
        val a = IntArray(n) { MAX_VAL }
        val result = maximumPairwiseProduct(a).toLong()
        val correct = calcAnswer(a)
        assertEquals(correct, result, "Maximal test")
    }

    private fun calcAnswer(a: IntArray): Long {
        a.sortDescending()
        return a[0].toLong() * a[1]
    }

}
