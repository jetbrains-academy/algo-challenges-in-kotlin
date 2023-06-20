

fun countSegmentsForEach(points: IntArray, segments: Array<Pair<Int, Int>>): IntArray {
    val segmentsCnt = segments.size
    val start = segments.map { it.first }.sorted()
    val end = segments.map { it.second }.sorted()
    val pointsCnt = points.size
    val pointIndices = points.indices.sortedBy { points[it] }
    val answer = IntArray(pointsCnt)
    var endsBefore = 0
    var startsAfter = 0
    for (index in pointIndices) {
        val x = points[index]
        while (endsBefore < segmentsCnt && end[endsBefore] < x) {
            ++endsBefore
        } // end[endsBefore] >= x && end[endsBefore - 1] < x
        while (startsAfter < segmentsCnt && start[startsAfter] <= x) {
            ++startsAfter
        } // start[startsAfter] > x && start[startsAfter - 1] <= x
        val outside = endsBefore + (segmentsCnt - startsAfter)
        val inside = segmentsCnt - outside
        answer[index] = inside
    }
    return answer
}
