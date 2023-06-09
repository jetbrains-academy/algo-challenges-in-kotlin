import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import java.time.Duration
import java.util.*
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class HiddenTests {

    companion object {

        private const val THRESHOLD = 1_000_000
        private const val MAX_N = 200
        private fun createBitSet(args: IntArray): BitSet {
            val result = BitSet()
            args.forEach { result.set(it) }
            return result
        }

        private fun nextSubset(set: IntArray, n: Int): Boolean {
            for (i in set.indices.reversed()) {
                if (set[i] + set.size - i != n) {
                    set[i]++
                    for (j in i + 1 until set.size) {
                        set[j] = set[j - 1] + 1
                    }
                    return true
                }
            }
            return false
        }

        private fun generateExpectedSubsets(n: Int, k: Int): List<BitSet> {
            val set = IntArray(k) { it }
            val result = mutableListOf<BitSet>()
            do {
                result.add(createBitSet(set))
            } while (nextSubset(set, n))
            return result
        }
    }

    @Test
    fun testAllUpTo10() {
        for (n in 1..10) {
            for (k in 0..n) {
                testSingle(n, k)
            }
        }
    }

    private fun testSingle(n: Int, k: Int) {
        val actual = runTimeout(2.seconds, "n = $n, k = $k") {
            generateSubsets(n, k)
        }
        val expected = generateExpectedSubsets(n, k)
        println("n = $n, k = $k, size = ${expected.size}")
        assertEquals(expected, actual) {
            "Wrong answer for n = $n, k = $k"
        }
    }

    @TestFactory
    fun testMaximal1(): List<DynamicTest> {
        return (1..11).map { k ->
            val n = findNUpToThreshold(k)
            listOf(DynamicTest.dynamicTest("test_${n}_$k") {
                testSingle(n, k)
            }, DynamicTest.dynamicTest("test_${n}_${n - k}") {
                testSingle(n, n - k)
            })
        }.flatten()
    }

    @TestFactory
    fun testMaximal2(): List<DynamicTest> {
        val rng = Random(239)
        val kValues = mutableListOf(0, MAX_N)
        kValues.addAll((12..MAX_N / 2).filter {
            rng.nextInt(6) == 5
        })
        kValues.addAll((MAX_N / 2 until MAX_N).filter {
            rng.nextInt(20) == 10
        })
        return kValues.map { k ->
            val n = findNUpToThreshold(k)
            DynamicTest.dynamicTest("test_${n}_$k") {
                testSingle(n, k)
            }
        }
    }

    private fun findNUpToThreshold(k: Int): Int {
        var n = k
        var combinations = 1L
        while (n <= MAX_N && combinations <= THRESHOLD) {
            n++
            combinations = combinations * n / (n - k)
        }
        return n - 1
    }
}