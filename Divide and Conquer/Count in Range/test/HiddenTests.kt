import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class HiddenTests {
    companion object {
        private val random = Random(228)
    }

    @Test
    fun example1() = runTimeout(1.seconds, "Example 1") {
        val result = countInRange(intArrayOf(2, 3, 5, 8, 10), 3, 9)
        Assertions.assertEquals(result, 3)
    }

    @Test
    fun example2() = runTimeout(1.seconds, "Example 2") {
        val result = countInRange(intArrayOf(2, 3, 5, 8, 10), 1, 20)
        Assertions.assertEquals(result, 5)
    }

    @Test
    fun example3() = runTimeout(1.seconds, "Example 3") {
        val result = countInRange(intArrayOf(2, 3, 5, 8, 10), 6, 7)
        Assertions.assertEquals(result, 0)
    }

    @Test
    fun small_random_tests() = runTimeout(2.seconds, "small_random_tests") {
        val TESTS_NUMBER = 100
        val MAX_N = 100
        val MAX_VAL = 1000
        for (i in 1..TESTS_NUMBER) {
            val n = random.nextInt(1, MAX_N)
            val a = IntArray(n) { random.nextInt(0, MAX_VAL) }
            a.sort()
            var l = random.nextInt(0, MAX_VAL)
            var r = random.nextInt(0, MAX_VAL)
            if (l > r) {
                val t = l
                l = r
                r = t
            }
            run_test(a, l, r)
        }
    }

    @Test
    fun large_random_tests() = runTimeout(2.seconds, "large_random_tests") {
        val TESTS_NUMBER = 10000
        val MAX_N = 1000000
        val MAX_VAL = 1000000000
        val n = MAX_N
        val a = IntArray(n) { random.nextInt(0, MAX_VAL) }
        a.sort()
        for (i in 1..TESTS_NUMBER) {
            var l = random.nextInt(0, MAX_VAL)
            var r = random.nextInt(0, MAX_VAL)
            if (l > r) {
                val t = l
                l = r
                r = t
            }
            run_test(a, l, r)
        }
    }

    private fun run_test(a: IntArray, l: Int, r: Int) {
        val result = countInRange(a, l, r)
        val correct = modelSolution(a, l, r)
        Assertions.assertEquals(result, correct)
    }

    private fun modelSolution(a: IntArray, l: Int, r: Int): Int {
        return lowerBound(a, r + 1) - lowerBound(a, l)
    }

    private fun lowerBound(a: IntArray, x: Int): Int {
        var lower = -1
        var upper = a.size
        while (upper > lower + 1) {
            val middle = (lower + upper) / 2
            if (a[middle] < x) {
                lower = middle
            } else {
                upper = middle
            }
        }
        return upper
    }
}
