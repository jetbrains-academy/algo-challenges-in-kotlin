import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun sample1() {
        assertEquals("madam", findLongestPalindromicSubsequence("bmczhadaem").toString())
    }

    @Test
    fun sample2() {
        assertEquals("abacaba", findLongestPalindromicSubsequence("abacaba").toString())
    }

    @Test
    fun sample3() {
        val res = findLongestPalindromicSubsequence("kotlin")
        assertEquals(1, res.length)
        assertTrue("kotlin".contains(res))
    }
}
