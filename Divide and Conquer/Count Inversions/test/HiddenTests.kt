import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import java.util.concurrent.TimeUnit
import kotlin.random.Random

private fun <T> Random.getList(n: Int, next: (Random) -> T): List<T> {
    return generateSequence { next(this) }.take(n).toList()
}

private class InversionsCounter<T : Comparable<T>>(a: List<T>) {
    private val a = MutableList(a.size) { Pair(a[it], it) }
    private val b = mutableListOf<Pair<T, Int>>()
    private val result = IntArray(a.size)

    private fun mergeSort(left: Int, right: Int) {
        if (left + 1 >= right) {
            return
        }
        val mid = (left + right) shr 1
        mergeSort(left, mid)
        mergeSort(mid, right)
        var i = left
        var j = mid
        while (i < mid || j < right) {
            if (j >= right || (i < mid && a[i].first <= a[j].first)) {
                b.add(a[i])
                i++
            } else {
                result[a[j].second] += mid - i
                b.add(a[j])
                j++
            }
        }
        for (pos in 0 until b.size) {
            a[pos + left] = b[pos]
        }
        b.clear()
    }

    fun count(): IntArray {
        mergeSort(0, a.size)
        return result
    }
}

private data class MyPair(val x: Int, val y: Int): Comparable<MyPair> {
    private fun computeKey() = x * 239017 + y
    override fun compareTo(other: MyPair) = computeKey().compareTo(other.computeKey())
}

class HiddenTests {

    companion object {
        const val MAX_LENGTH = 1_000_000
    }

    @Test
    fun testSortedLongs() {
        val rng = Random(12L)
        for (length in 1..100) {
            val a = rng.getList(length) {
                it.nextLong(0, Long.MAX_VALUE)
            }.sorted()
            checkList(a)
        }
    }

    @Test
    fun testBackSortedLongs() {
        val rng = Random(12L)
        for (length in 1..100) {
            val a = rng.getList(length) {
                it.nextLong(0, Long.MAX_VALUE)
            }.sorted().reversed()
            checkList(a)
        }
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun testRandomUpTo1000() {
        val rng = Random(13L)
        for (length in 1..1000) {
            val a = rng.getList(length) {
                it.nextLong(0, Long.MAX_VALUE)
            }
            checkList(a)
        }
    }

    private fun <T : Comparable<T>> checkList(a: List<T>) {
        val expected = InversionsCounter(a).count()
        val actual = findInversions(a)
        assertArrayEquals(expected, actual) {
            "n = ${a.size}"
        }
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun maxAnswer() {
        val a = Random(14L).getList(MAX_LENGTH) { rng ->
            generateString(10, rng)
        }.sorted().reversed()
        checkList(a)
    }

    private fun <T : Comparable<T>> maxTest(seed: Long, generateElement: (Random) -> T) {
        val rng = Random(seed)
        val a = rng.getList(MAX_LENGTH, generateElement)
        checkList(a)
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun maxTestStrings() {
        maxTest(15L) { rng ->
            generateString(7, rng)
        }
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun maxTestLongs() {
        maxTest(16L) { rng ->
            rng.nextLong(Long.MIN_VALUE, Long.MAX_VALUE)
        }
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun maxTestDouble() {
        maxTest(17L) { rng ->
            rng.nextDouble()
        }
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun maxTestMyPair() {
        maxTest(18L) { rng ->
            MyPair(rng.nextInt(), rng.nextInt())
        }
    }

    private fun generateString(maxLength: Int, rng: Random): String {
        val length = rng.nextInt(maxLength)
        return generateSequence { ('a'.code + rng.nextInt(26)).toChar() }.take(length).joinToString("")
    }

    // TODO add some other tests as well
}
