

private class PartitionGenerator(private val n: Int) {
    private val currentPartition = mutableListOf<Int>()
    private val generated = mutableListOf<Partition>()

    private fun recursiveGenerate(lastTerm: Int, sumLeft: Int) {
        if (sumLeft == 0) {
            generated.add(Partition(currentPartition.toList()))
            return
        }
        if (lastTerm > sumLeft) {
            return;
        }
        for (currentTerm in lastTerm..sumLeft) {
            currentPartition.add(currentTerm)
            recursiveGenerate(currentTerm, sumLeft - currentTerm)
            currentPartition.removeLast()
        }
    }

    fun generate(): List<Partition> {
        if (generated.isEmpty()) {
            recursiveGenerate(1, n)
        }
        return generated
    }
}

fun generatePartitions(n: Int): List<Partition> {
    return PartitionGenerator(n).generate()
}
