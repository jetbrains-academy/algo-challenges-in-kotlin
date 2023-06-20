import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.time.Duration.Companion.seconds

class HiddenTests {

    companion object {
        private const val MAX_N = 300_000
        private const val MAX_COORDINATES = 1_000_000_000
    }

    private val rng = Random(239)

    private class Testcase(val points: IntArray, val segments: Array<Pair<Int, Int>>) {
        fun solve(): IntArray {
            val segCount = segments.size
            val begins = IntArray(segCount) { segments[it].first }
            val ends = IntArray(segCount) { segments[it].second }
            begins.sort()
            ends.sort()
            val indexes = points.indices.sortedBy { points[it] }
            var currentBegin = 0
            var currentEnd = 0
            val answer = IntArray(points.size)
            for (index in indexes) {
                val x = points[index]
                while (currentBegin < segCount && begins[currentBegin] <= x) {
                    currentBegin++
                }
                while (currentEnd < segCount && ends[currentEnd] < x) {
                    currentEnd++
                }
                answer[index] = currentBegin - currentEnd
            }
            return answer
        }

        fun validate(): Boolean {
            return segments.all { it.first <= it.second }
        }
    }

    private fun testSingle(test: Testcase, msg: String, withTimeout: Boolean = true) {
        check(test.validate())
        val expected = test.solve()
        val actual = runIfTimeout(withTimeout, 2.seconds, msg) {
            countSegmentsForEach(test.points, test.segments)
        }
        assertArrayEquals(expected, actual) {
            "Wrong answer for test [$msg]"
        }
    }

    private fun ints(n: Int, limit: Int) = IntArray(n) {
        rng.nextInt(-limit..limit)
    }

    private fun pairs(n: Int, limit: Int) = Array(n) {
        val from = rng.nextInt(-limit..limit)
        val to = rng.nextInt(from..limit)
        from to to
    }

    private fun randomTest(pCount: Int, sCount: Int, limit: Int) = Testcase(
        points = ints(pCount, limit),
        segments = pairs(sCount, limit),
    )

    @Test
    fun randomSmall() = runTimeout(5.seconds, "100k tests cnt <= 10") {
        repeat(100000) {
            val pCount = rng.nextInt(1..10)
            val sCount = rng.nextInt(1..10)
            val test = randomTest(pCount, sCount, 10)
            val msg = "points = ${test.points.contentToString()}, " +
                    "segments = ${test.segments.contentToString()}"
            testSingle(test, msg, withTimeout = false)
        }
    }


    @Test
    fun randomUpTo100() {
        batchRandom(10_000, 1..10, 10)
    }

    @Test
    fun randomUpTo1000() {
        batchRandom(500, 101..1000)
    }

    @Test
    fun randomUpTo10000() {
        batchRandom(100, 1001..10_000)
    }

    @Test
    fun randomUpTo50000() {
        batchRandom(20, 10_001..50_000)
    }

    @Test
    fun randomUpTo100000() {
        batchRandom(10, 50_001..100_000)
    }

    private fun batchRandom(times: Int, cntRange: IntRange, limit: Int = MAX_COORDINATES) {
        runTimeout(5.seconds, "$times tests, cnt <= ${cntRange.last}") {
            repeat(times) {
                val pCount = rng.nextInt(cntRange)
                val sCount = rng.nextInt(cntRange)
                val test = randomTest(pCount, sCount, limit)
                val msg = "random test: $pCount points, $sCount segments"
                testSingle(test, msg, withTimeout = false)
            }
        }
    }

    @Test
    fun maxRandomTest() {
        testSingle(randomTest(MAX_N, MAX_N, MAX_COORDINATES), "Random max test")
    }
}
