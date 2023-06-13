import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun sample1() {
        val actual = change(2)
        assertEquals(2, actual) {
            "money = 2"
        }
    }

    @Test
    fun sample2() {
        val money = 28
        val actual = change(money)
        assertEquals(6, actual) {
            "money = $money"
        }
    }
}
