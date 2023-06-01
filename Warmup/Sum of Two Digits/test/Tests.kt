import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun sample1() {
        val a = 9
        val b = 7
        val correct = 16
        val result = sumOfTwoDigits(a, b)
        assertEquals(result, correct, "countOnes($a, $b)")
    }

}
