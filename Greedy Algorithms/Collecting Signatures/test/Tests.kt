import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun sample1() {
        test(listOf(
            Segment(1, 3),
            Segment(2, 5),
            Segment(3, 6)),
            1)
    }

    @Test
    fun sample2() {
        test(listOf(
            Segment(4, 7),
            Segment(1, 3),
            Segment(2, 5),
            Segment(5, 6)),
            2)
    }

    fun test(segments: List<Segment>, answer: Int) {
        val output = segmentsCover(segments)
        checkCorrectness(segments, output)
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
}
