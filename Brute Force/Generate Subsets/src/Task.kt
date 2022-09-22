import java.util.BitSet

private class SubsetGenerator(private val size: Int, private val subSize: Int) {
    private val currentSubset = BitSet(size)
    private val allSubsets = mutableListOf<BitSet>()

    private fun doGenerate(index: Int, used: Int) {
        if (index == size) {
            allSubsets.add(currentSubset.clone() as BitSet)
            return
        }
        if (used < subSize) {
            currentSubset.set(index)
            doGenerate(index + 1, used + 1)
            currentSubset.set(index, false)
        }
        if (size - index > subSize - used) {
            doGenerate(index + 1, used)
        }
    }

    fun generate(): List<BitSet> {
        if (allSubsets.isEmpty()) {
            doGenerate(0, 0)
        }
        return allSubsets
    }
}

fun generateSubsets(n: Int, k: Int): List<BitSet> {
    return SubsetGenerator(n, k).generate()
}
