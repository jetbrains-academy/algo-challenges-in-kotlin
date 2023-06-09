import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.math.max
import kotlin.time.Duration.Companion.seconds

class HiddenTests {

    companion object {
        private const val MAX_N = 60

        private fun computePartitionCounts(): IntArray {
            val upTo = MAX_N
            val count = Array(upTo + 1) { IntArray(upTo + 1) }
            count[0][0] = 1
            for (sum in 0 until upTo) {
                for (maxTerm in 0..sum) {
                    val value = count[sum][maxTerm]
                    if (value == 0) continue
                    for (nextTerm in max(1, maxTerm)..(upTo - sum)) {
                        count[sum + nextTerm][nextTerm] += value
                    }
                }
            }
            return count.map { it.sum() }.toIntArray()
        }

        private val partitionsCount = computePartitionCounts()
    }


    private fun canonicalize(p: Partition) = Partition(p.terms.sorted())

    private fun checkPartitions(expected: Int, n: Int, found: List<Partition>) {
        Assertions.assertEquals(expected, found.size) {
            "[n = $n] Wrong number of partitions"
        }
        val unique = found.map(this::canonicalize).toCollection(mutableSetOf())
        Assertions.assertTrue(unique.size == found.size) {
            "[n = $n] Partitions are not unique"
        }
        unique.forEach { p ->
            p.terms.forEach {
                Assertions.assertTrue(it in 1..n) {
                    "[n = $n] Wrong terms in partition expected in [1, $n] found $it"
                }
            }
            val partitionSum = p.terms.sum()
            Assertions.assertEquals(n, partitionSum) {
                "[n = $n] Wrong sum"
            }
        }
        println("n = $n, partitions found = ${found.size}")
    }

    @Test
    fun testAllUpTo50() = runTimeout(5.seconds, "All n up to 50") {
        val upTo = 50
        for (sum in 1..upTo) {
            val actual = generatePartitions(sum)
            checkPartitions(partitionsCount[sum], sum, actual)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [51, 52, 53, 54, 55, 56, 57, 58, 59, 60])
    fun testBig(n: Int) {
        val actual = runTimeout(2.seconds, "n = $n") {
            generatePartitions(n)
        }
        checkPartitions(partitionsCount[n], n, actual)
    }
}
