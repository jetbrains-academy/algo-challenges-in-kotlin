import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Tests {

    private fun canonicalize(p: Partition) = Partition(p.terms.sorted())

    private fun checkPartitions(expected: Int, n: Int, found: List<Partition>) {
        assertEquals(expected, found.size) {
            "Wrong number of partitions"
        }
        val unique = found.map(this::canonicalize).toCollection(mutableSetOf())
        assertTrue(unique.size == found.size) {
            "Partitions are not unique"
        }
        unique.forEach { p ->
            p.terms.forEach {
                assertTrue(it in 1..n) {
                    "Wrong terms in partition expected in [1, $n] found $it"
                }
            }
            val partitionSum = p.terms.sum()
            assertEquals(n, partitionSum) {
                "Wrong sum"
            }
        }
    }

    @Test
    fun sample() {
        val actual = generatePartitions(4)
        checkPartitions(5, 4, actual)
    }
}
