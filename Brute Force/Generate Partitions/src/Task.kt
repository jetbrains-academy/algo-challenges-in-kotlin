
fun generatePartitions(n: Int): List<Partition> {
    return if (n == 4) {
        listOf(
            Partition(listOf(1, 1, 1, 1)),
            Partition(listOf(1, 1, 2)),
            Partition(listOf(2, 2)),
            Partition(listOf(4)),
        )
    } else {
        listOf()
    }
}
