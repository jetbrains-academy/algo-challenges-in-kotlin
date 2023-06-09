import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.math.max
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class HiddenTests {

    companion object {
        private val rng = Random(239L)
        private const val MAX_LENGTH = 5000

        private fun validate(s: String): Boolean {
            return s.length <= MAX_LENGTH && s.all { it.isLowerCase() }
        }

        private fun randomLetter(alphabet: Int) = (rng.nextInt(alphabet) + 'a'.code).toChar()

        private fun randomString(length: Int, alphabet: Int): String {
            return (1..length)
                .map { randomLetter(alphabet) }
                .joinToString("")
        }

        private fun getLengthOfLongest(s: String): Int {
            val longest = Array(s.length) { IntArray(s.length) }
            for (i in s.indices) {
                longest[i][i] = 1
            }
            for (left in s.length - 2 downTo 0) {
                val current = longest[left]
                val next = longest[left + 1]
                for (right in left + 1 until s.length) {
                    current[right] = max(next[right], current[right - 1])
                    if (s[left] == s[right]) {
                        current[right] = max(current[right], next[right - 1] + 2)
                    }
                }
            }
            return longest[0][s.length - 1]
        }

        private fun isPalindrome(sequence: String): Boolean = sequence == sequence.reversed()

        private fun isSubsequence(sub: String, of: String): Boolean {
            if (sub.isEmpty()) {
                return true
            }
            var indexInSub = 0
            for (c in of) { // indexInSub < sub.length
                if (sub[indexInSub] == c) {
                    indexInSub++
                    if (indexInSub == sub.length) {
                        return true
                    }
                }
            }
            return false
        }

        private fun shorten(seq: String): String {
            val shortened = if (seq.length > 10) {
                seq.substring(0 until 4) + "..." + seq.substring(seq.length - 4)
            } else {
                seq
            }
            return "\"" + shortened + "\""
        }
    }

    private fun testSingle(s: String, message: String, withTimeout: Boolean = true) {
        check(validate(s))
        val found = runIfTimeout(withTimeout, 1.seconds, message) {
            findLongestPalindromicSubsequence(s).toString()
        }
        assertTrue(isPalindrome(found)) { "Test [$message]: not a palindrome found -- ${shorten(found)}" }
        assertTrue(isSubsequence(found, s)) { "Test [$message]: is not a subsequence found -- ${shorten(found)}" }
        val lengthOfLongest = getLengthOfLongest(s)
        assertTrue(lengthOfLongest <= found.length) {
            "Test [$message]: not the longest palindrome found -- ${
                shorten(
                    found
                )
            }, there is one of length $lengthOfLongest"
        }
        assertTrue(lengthOfLongest == found.length) {
            "Test [$message]: something wrong happened, contact the course developers"
        }
    }

    @Test
    fun testAllBinaryUpTo10() = runTimeout(2.seconds, "All binary strings up to length of 10") {
        for (length in 1..10) {
            for (mask in 0 until (1 shl length)) {
                val input = buildString {
                    for (index in 0 until length) {
                        append((((mask shr index) and 1) + 'a'.code).toChar())
                    }
                }
                testSingle(input, "\"" + input + "\"", withTimeout = false)
            }
        }
    }

    @Test
    fun testAllTertiaryUpTo8() = runTimeout(2.seconds, "All strings consisting of 'a', 'b', and 'c', up to length 8") {
        var count = 1
        for (length in 1..8) {
            count *= 3
            for (mask in 0 until count) {
                val input = buildString {
                    var current = mask
                    for (index in 0 until length) {
                        append((current % 3 + 'a'.code).toChar())
                        current /= 3
                    }
                }
                testSingle(input, "\"" + input + "\"", withTimeout = false)
            }
        }
    }

    @Test
    fun testEmpty() {
        testSingle("", "Empty string")
    }

    @ParameterizedTest
    @ValueSource(ints = [20, 50, 100, MAX_LENGTH - 1, MAX_LENGTH])
    fun testSingleLetterAlphabet(length: Int) {
        testSingle("a".repeat(length), "Single letter repeated $length times")
    }

    @ParameterizedTest
    @ValueSource(ints = [2, 3, 4, 5, 6, 10, 26])
    fun randomSmall(alphabet: Int) = runTimeout(2.seconds, "Random tests of length up to 50") {
        repeat(100) {
            val input = randomString(rng.nextInt(50) + 1, alphabet)
            testSingle(input, shorten(input), withTimeout = false)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [2, 3, 4, 5, 6, 10, 26])
    fun randomMedium(alphabet: Int) = runTimeout(2.seconds, "Random tests of length up to 500") {
        repeat(10) {
            val input = randomString(rng.nextInt(400) + 101, alphabet)
            testSingle(input, shorten(input), withTimeout = false)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [2, 3, 4, 5, 6, 10, 26])
    fun randomMax(alphabet: Int) {
        val input = randomString(MAX_LENGTH, alphabet)
        testSingle(input, "Random test of maximum length")
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4, 5, 6])
    fun testBigPalindromeMutated(mutations: Int) {
        val alphabet = 26
        val input = randomString(MAX_LENGTH / 2, alphabet).let {
            it + if (rng.nextBoolean()) {
                it.reversed()
            } else {
                it.reversed().substring(1)
            }
        }.toCharArray()
        repeat(mutations) {
            val index = rng.nextInt(input.size)
            input[index] = randomLetter(alphabet)
        }
        testSingle(input.concatToString(), "Test with a big palindrome")
    }
}
