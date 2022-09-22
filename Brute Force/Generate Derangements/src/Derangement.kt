
data class Derangement(private val permutation: IntArray) {
    override fun toString(): String {
        return permutation.contentToString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Derangement
        if (!permutation.contentEquals(other.permutation)) return false
        return true
    }

    override fun hashCode(): Int {
        return permutation.contentHashCode()
    }
}