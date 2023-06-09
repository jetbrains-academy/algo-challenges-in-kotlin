import kotlin.math.min

fun changeMoney(money: Int): Int {
    val table = IntArray(money + 1) { Int.MAX_VALUE }
    table[0] = 0
    for (m in 1..money) {
        for (c in sequenceOf(1, 3, 4)) {
            if (c <= m) {
                table[m] = min(table[m], 1 + table[m - c])
            }
        }
    }
    return table[money]
}
