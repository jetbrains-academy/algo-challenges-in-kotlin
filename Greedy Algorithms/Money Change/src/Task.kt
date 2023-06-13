fun change(money: Int): Int {
    var current = money
    var coins = 0
    while (current >= 10) {
        current -= 10
        coins++
    }
    while (current >= 5) {
        current -= 5
        coins++
    }
    coins += current
    return coins
}
