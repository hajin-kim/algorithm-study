// https://www.acmicpc.net/problem/1072 s3

fun main(args: Array<String>) {
    val (nGames, nWins) = readln().split(" ").map { it.toLong() }

    val rate = nWins * 100 / nGames

    if (rate >= 99) {
        println(-1)
        return
    }

    var left = 0L
    var right = 1000000000L

    while (left < right) {
        val mid = (left + right) / 2

        val newZ = (nWins + mid) * 100 / (nGames + mid)

        if (newZ > rate) {
            right = mid
        } else {
            left = mid + 1
        }
    }

    println(left)
}
