data class Segment(val l: Int, val r: Int)

fun segmentsCover(segments: List<Segment>): List<Int> {
    val res = ArrayList<Int>()
    for (segment in segments.sortedBy { it.r }) {
        if (res.isEmpty() || res.last() < segment.l) {
            res.add(segment.r)
        }
    }
    return res
}
