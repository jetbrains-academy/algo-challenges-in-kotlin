
private class BinaryStringGenerator(private val length: Int) {
    private val currentString = CharArray(length)
    private val allStrings = mutableListOf<String>()

    private fun doGenerate(index: Int) {
        if (index == length) {
            allStrings.add(String(currentString))
            return
        }
        currentString[index] = '0'
        doGenerate(index + 1)
        currentString[index] = '1'
        doGenerate(index + 1)
    }

    fun generate(): List<String> {
        if (allStrings.isEmpty()) {
            doGenerate(0)
        }
        return allStrings
    }
}

fun generateBinaryStrings(length: Int): List<String> {
    return BinaryStringGenerator(length).generate()
}
