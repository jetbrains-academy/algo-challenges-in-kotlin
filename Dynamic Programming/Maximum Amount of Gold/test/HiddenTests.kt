import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.seconds

class HiddenTests {

    private val rng = Random(239)

    class Bitset(capacity: Int) {
        private val bits = LongArray(capacity)

        private constructor(bits: LongArray) : this(bits.size) {
            bits.copyInto(this.bits)
        }

        companion object {
            private fun shiftLeft(bits: LongArray, x: Int): LongArray {
                val result = LongArray(bits.size)
                var carry = 0L
                val shift = x shr 6
                val take = x and 63
                val to = result.size - shift
                for (index in 0 until to) {
                    val current = bits[index]
                    val newCarry = if (take == 0) 0 else current ushr -x
                    result[index + shift] = (current shl take) or carry
                    carry = newCarry
                }
                return result
            }
        }

        infix fun shl(x: Int): Bitset {
            val newBits = shiftLeft(bits, x)
            return Bitset(newBits)
        }

        infix fun or(other: Bitset): Bitset {
            check(other.bits.size == bits.size)
            val newBits = LongArray(bits.size)
            for (index in newBits.indices) {
                newBits[index] = bits[index] or other.bits[index]
            }
            return Bitset(newBits)
        }

        fun get(index: Int): Boolean {
            return ((bits[index shr 6] ushr index) and 1) == 1L
        }

        fun set(index: Int): Bitset {
            val newBits = bits.clone()
            newBits[index shr 6] = bits[index shr 6] or (1L shl index)
            return Bitset(newBits)
        }

        override fun toString(): String {
            return bits.joinToString { it.toString(2).padStart(64, '0').reversed() }.reversed()
        }
    }

    companion object {

        private const val MAX_ITEMS = 500
        private const val MAX_WEIGHT = 10_000
        fun calculateSolution(backpackCapacity: Int, weights: IntArray): Int {
            var f = Bitset((backpackCapacity shr 6) + 1)
            f = f.set(0)
            for (x in weights) {
                f = f.or(f.shl(x))
            }
            var answer = backpackCapacity
            while (!f.get(answer)) {
                answer--
            }
            return answer
        }
    }

    class Testcase(val capacity: Int, val weights: IntArray) {
        fun validate(): Boolean {
            return capacity in 1..MAX_WEIGHT
                    && weights.size in 0..MAX_ITEMS
                    && weights.all { it in 1..MAX_WEIGHT }
        }
    }

    private fun testSingle(test: Testcase, msg: String, withTimeout: Boolean = true) {
        check(test.validate())
        val expected = calculateSolution(test.capacity, test.weights)
        val actual = runIfTimeout(withTimeout, 1.seconds, msg) {
            findMaximumAmountOfGold(test.capacity, test.weights)
        }
        if (withTimeout) {
            println("capacity = ${test.capacity}, answer = $expected")
        }
        assertEquals(expected, actual) {
            "Test [$msg]: wrong answer"
        }
    }

    @Test
    fun testSmallRandomTests() = runTimeout(5.seconds, "500k, everything <= 15") {
        repeat(500000) {
            val count = rng.nextInt(1..15)
            val capacity = rng.nextInt(1..15)
            val weights = IntArray(count) { rng.nextInt(1..15) }
            val test = Testcase(capacity, weights)
            val msg = "capacity = $capacity, weights = ${weights.contentToString()}"
            testSingle(test, msg, withTimeout = false)
        }
    }

    @Test
    fun testMediumRandomTests() = runTimeout(5.seconds, "100k, everything <= 40") {
        repeat(100000) {
            val count = rng.nextInt(1..40)
            val capacity = rng.nextInt(16..40)
            val weights = IntArray(count) { rng.nextInt(1..40) }
            val test = Testcase(capacity, weights)
            val msg = "capacity = $capacity, weights = ${weights.contentToString()}"
            testSingle(test, msg, withTimeout = false)
        }
    }

    @Test
    fun testMediumRandomTests2() = runTimeout(5.seconds, "10k, everything <= 100") {
        repeat(10000) {
            val count = rng.nextInt(1..100)
            val capacity = rng.nextInt(50..100)
            val weights = IntArray(count) { rng.nextInt(1..100) }
            val test = Testcase(capacity, weights)
            val msg = "capacity = $capacity, weights = ${weights.contentToString()}"
            testSingle(test, msg, withTimeout = false)
        }
    }

    @Test
    fun testMaxTest1() {
        val test = Testcase(
            capacity = MAX_WEIGHT,
            weights = IntArray(MAX_ITEMS) { MAX_WEIGHT }
        )
        testSingle(test, "capacity = $MAX_WEIGHT, all weights = $MAX_WEIGHT")
    }

    @Test
    fun testMaxTest2() {
        val test = Testcase(
            capacity = MAX_WEIGHT,
            weights = IntArray(MAX_ITEMS) { MAX_WEIGHT - 1 }
        )
        testSingle(test, "capacity = $MAX_WEIGHT, all weights = ${MAX_WEIGHT - 1}")
    }

    @Test
    fun testMaxTest3() {
        val weights = IntArray(MAX_ITEMS) {
            when {
                it < 15 -> rng.nextInt(1, MAX_WEIGHT)
                else -> 1
            }
        }
        val test = Testcase(
            capacity = MAX_WEIGHT,
            weights = weights
        )
        testSingle(test, "capacity = $MAX_WEIGHT, 15 items of random weight, others of weight = 1")
    }

    @Test
    fun testMaxTest4() {
        val chooseFrom = intArrayOf(MAX_WEIGHT / 3 + 100, MAX_WEIGHT / 2 + 50, MAX_WEIGHT / 4 + 133)
        val weights = IntArray(MAX_ITEMS) { chooseFrom.random(rng) }
        val test = Testcase(
            capacity = MAX_WEIGHT,
            weights = weights
        )
        testSingle(test, "capacity = $MAX_WEIGHT, all items of random among ${chooseFrom.contentToString()}")
    }


    @Test
    fun testMaxTest5() {
        val chooseFrom = intArrayOf(MAX_WEIGHT / 3 + 101, MAX_WEIGHT / 2 + 50, MAX_WEIGHT / 4 + 133, MAX_WEIGHT / 10 + 222)
        val weights = IntArray(MAX_ITEMS) { chooseFrom.random(rng) }
        val test = Testcase(
            capacity = MAX_WEIGHT,
            weights = weights
        )
        testSingle(test, "capacity = $MAX_WEIGHT, all items of random among ${chooseFrom.contentToString()}")
    }

    @Test
    fun testBigRandomTests() = runTimeout(5.seconds) {
        repeat(30) {
            val count = rng.nextInt(300..500)
            val capacity = rng.nextInt(5000..10_000)
            val weights = IntArray(count) { rng.nextInt(1..10_000) }
            val test = Testcase(capacity, weights)
            val msg = "capacity = $capacity, weights = ${weights.contentToString()}"
            testSingle(test, msg, withTimeout = false)
        }
    }

    @Test
    fun testFewItems() = runTimeout(5.seconds) {
        repeat(1000) {
            val count = rng.nextInt(3..5)
            val capacity = rng.nextInt(5000..10_000)
            val weights = IntArray(count) { rng.nextInt(1..10_000) }
            val test = Testcase(capacity, weights)
            val msg = "capacity = $capacity, weights = ${weights.contentToString()}"
            testSingle(test, msg, withTimeout = false)
        }
    }

    @Test
    fun testFewItemsSmall() = runTimeout(5.seconds) {
        repeat(100000) {
            val count = rng.nextInt(3..5)
            val capacity = rng.nextInt(65..128)
            val weights = IntArray(count) { rng.nextInt(1..128) }
            val test = Testcase(capacity, weights)
            val msg = "capacity = $capacity, weights = ${weights.contentToString()}"
            testSingle(test, msg, withTimeout = false)
        }
    }
}
