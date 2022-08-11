import kotlin.math.min

private class EditDistanceComputer(private val first: CharSequence, private val second: CharSequence) {
    companion object {
        const val NOT_FOUND = -1
    }

    private val distance = Array(first.length) {
        IntArray(second.length) { NOT_FOUND }
    }

    private fun computeRecursively(pref1: Int, pref2: Int): Int {
        if (pref1 == 0 || pref2 == 0) {
            return pref1 + pref2
        }
        // firstPrefix > 0 && secondPrefix > 0
        if (distance[pref1 - 1][pref2 - 1] != NOT_FOUND) {
            return distance[pref1 - 1][pref2 - 1]
        }
        val subCost = if (first[pref1 - 1] == second[pref2 - 1]) 0 else 1
        val result = min(
            min(
                computeRecursively(pref1 - 1, pref2) + 1,
                computeRecursively(pref1, pref2 - 1) + 1
            ),
            computeRecursively(pref1 - 1, pref2 - 1) + subCost
        )
        distance[pref1 - 1][pref2 - 1] = result // don't forget to memoize
        return result
    }

    fun compute(): Int {
        return computeRecursively(first.length, second.length)
    }
}

fun findEditDistance(first: CharSequence, second: CharSequence): Int {
    return EditDistanceComputer(first, second).compute()
}
