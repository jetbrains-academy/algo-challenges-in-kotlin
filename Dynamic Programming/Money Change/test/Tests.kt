import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun sample1() {
        val actual = changeMoney(15)
        assertEquals(4, actual)
    }

    @Test
    fun sample2() {
        val actual = changeMoney(26)
        assertEquals(7, actual)
    }
}