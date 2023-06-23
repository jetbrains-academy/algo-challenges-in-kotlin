import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class HiddenTests {

    companion object {
        private const val MAX_N = 1000000
        private const val MAX_VAL = 1000000
    }

    @Test
    fun sample1() = runTimeout(1.seconds, "sample 1") {
        val a = intArrayOf(1, 2, 3)
        val correct = 6L
        val result = maximumPairwiseProduct(a)
        assertEquals(result, correct, "maximumPairwiseProduct(${a.contentToString()})")
    }

    @Test
    fun sample2() = runTimeout(1.seconds, "sample 2") {
        val a = intArrayOf(7, 5, 14, 2, 8, 8, 10, 1, 2, 3)
        val correct = 140L
        val result = maximumPairwiseProduct(a)
        assertEquals(result, correct, "maximumPairwiseProduct(${a.contentToString()})")
    }

    @Test
    fun sample3() = runTimeout(1.seconds, "sample 3") {
        val a = intArrayOf(1_000_000, 1_000_000)
        val correct = 1_000_000_000_000L
        val result = maximumPairwiseProduct(a)
        assertEquals(correct, result, "maximumPairwiseProduct(${a.contentToString()}")
    }

    @Test
    fun small_random_tests() = runTimeout(2.seconds, "small_random_tests") {
        val TESTS_NUMBER = 100
        val rand = Random(239)
        for (i in 1..TESTS_NUMBER) {
            val n = rand.nextInt(2, 100)
            val a = IntArray(n) { rand.nextInt(0, MAX_VAL) }
            val result = maximumPairwiseProduct(a)
            val correct = calcAnswer(a)
            assertEquals(correct, result, "Random test $i")
        }
    }

    @Test
    fun large_random_tests() = runTimeout(2.seconds, "large_random_tests") {
        val TESTS_NUMBER = 5
        val rand = Random(239)
        for (i in 1..TESTS_NUMBER) {
            val n = rand.nextInt(2, MAX_N)
            val a = IntArray(n) { rand.nextInt(0, MAX_VAL) }
            val result = maximumPairwiseProduct(a)
            val correct = calcAnswer(a)
            assertEquals(correct, result, "Random test $i")
        }
    }

    @Test
    fun maximal_tests() = runTimeout(1.seconds, "maximal_test") {
        val n = MAX_N
        val a = IntArray(n) { MAX_VAL }
        val result = maximumPairwiseProduct(a)
        val correct = calcAnswer(a)
        assertEquals(correct, result, "Maximal test")
    }

    private fun calcAnswer(a: IntArray): Long {
        var firstMax = Int.MIN_VALUE
        var secondMax = Int.MIN_VALUE
        for (x in a) {
            if (x > firstMax) {
                secondMax = firstMax
                firstMax = x
            } else if (x > secondMax) {
                secondMax = x
            }
        }
        return firstMax.toLong() * secondMax
    }

}
