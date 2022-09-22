
private class DerangementsGenerator(private val length: Int) {
    private val currentDerangement = IntArray(length)
    private val allDerangements = mutableListOf<Derangement>()
    private val used = BooleanArray(length)

    private fun doGenerate(index: Int) {
        if (index == length) {
            allDerangements.add(Derangement(currentDerangement.clone()))
            return
        }
        for (value in 0 until length) {
            if (value == index || used[value]) {
                continue
            }
            used[value] = true
            currentDerangement[index] = value
            doGenerate(index + 1)
            used[value] = false
        }
    }

    fun generate(): List<Derangement> {
        if (allDerangements.isEmpty()) {
            doGenerate(0)
        }
        return allDerangements
    }
}

fun generateDerangements(n: Int): List<Derangement> {
    return DerangementsGenerator(n).generate()
}