import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class HiddenTests {
    @Test
    fun sample1() = runTimeout(1.seconds, "sample 1") {
        test(
            listOf(
                Segment(1, 3),
                Segment(2, 5),
                Segment(3, 6)
            )
        )
    }

    @Test
    fun sample2() = runTimeout(1.seconds, "sample 2") {
        test(
            listOf(
                Segment(4, 7),
                Segment(1, 3),
                Segment(2, 5),
                Segment(5, 6)
            )
        )
    }

    @Test
    fun smallRandomTests() = runTimeout(2.seconds, "small random tests") {
        val TESTS_NUMBER = 100
        val MAX_N = 10
        val MAX_T = 100
        val rand = Random(239)
        for (i in 1..TESTS_NUMBER) {
            val n = rand.nextInt(2, MAX_N)
            val a = List<Segment>(n) {
                var l = rand.nextInt(0, MAX_T - 1)
                var r = rand.nextInt(0, MAX_T - 1)
                if (r < l) {
                    val t = l
                    l = r
                    r = t
                }
                Segment(l, r + 1)
            }
            test(a)
        }
    }

    @Test
    fun largeRandomTests() = runTimeout(2.seconds, "large random tests") {
        val TESTS_NUMBER = 5
        val MAX_N = 100
        val MAX_T = 1000000000
        val rand = Random(239)
        for (i in 1..TESTS_NUMBER) {
            val n = rand.nextInt(2, MAX_N)
            val a = List<Segment>(n) {
                var l = rand.nextInt(0, MAX_T - 1)
                var r = rand.nextInt(0, MAX_T - 1)
                if (r < l) {
                    val t = l
                    l = r
                    r = t
                }
                Segment(l, r + 1)
            }
            test(a)
        }
    }

    fun test(segments: List<Segment>) {
        val output = segmentsCover(segments)
        checkCorrectness(segments, output)
        val answer = modelSolution(segments).size
        assertEquals(output.size, answer) {
            "Size of the answer is not optimal"
        }
    }

    fun checkCorrectness(segments: List<Segment>, points: List<Int>) {
        for (segment in segments) {
            var covered = false
            for (point in points) {
                if (point >= segment.l && point <= segment.r) {
                    covered = true
                }
            }
            assertTrue(covered) {
                "Segment $segment is not covered"
            }
        }
    }

    fun modelSolution(segments: List<Segment>): List<Int> {
        val res = ArrayList<Int>()
        for (segment in segments.sortedBy { it.r }) {
            if (res.isEmpty() || res.last() < segment.l) {
                res.add(segment.r)
            }
        }
        return res
    }

}
