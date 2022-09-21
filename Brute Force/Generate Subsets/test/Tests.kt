import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.BitSet

class Tests {

    companion object {
        private fun createBitSet(vararg args: Int): BitSet {
            val result = BitSet()
            args.forEach { result.set(it) }
            return result
        }
    }

    @Test
    fun sample1() {
        val actual = generateSubsets(2, 1)
        val expected = listOf(createBitSet(0), createBitSet(1))
        assertEquals(actual, expected)
    }

    @Test
    fun sample2() {
        val actual = generateSubsets(3, 2)
        val expected = listOf(
            createBitSet(0, 1),
            createBitSet(0, 2),
            createBitSet(1, 2)
        )
        assertEquals(actual, expected)
    }
}