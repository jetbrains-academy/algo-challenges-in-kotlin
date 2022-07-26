import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Test {

    @Test
    fun testSamples() {
        val firstSample = intArrayOf(1, 2, 3, 4, 5)
        assertEquals(
            16,
            findMinimumChange(firstSample),
            "First sample $firstSample",
        )
        val secondSample = intArrayOf(1, 1)
        assertEquals(
            3,
            findMinimumChange(secondSample),
            "Second sample $secondSample",
        )
        val thirdSample = intArrayOf(100, 1, 2)
        assertEquals(
            4,
            findMinimumChange(thirdSample),
            "Third sample $thirdSample",
        )
    }
}