import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HiddenTests {
    @Test
    fun sample1() {
        test(listOf(
            Segment(1, 3),
            Segment(2, 5),
            Segment(3, 6)))
    }

    @Test
    fun sample2() {
        test(listOf(
            Segment(4, 7),
            Segment(1, 3),
            Segment(2, 5),
            Segment(5, 6)))
    }

    fun test(segments: List<Segment>) {
        val output = segmentsCover(segments)
        checkCorrectness(segments, output)
        val answer = modelSolution(segments).size
        assertTrue(output.size == answer) {
            "Size of the answer is ${output.size}, but optimal solution is $answer"
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
