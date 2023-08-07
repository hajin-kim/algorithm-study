import java.util.*

// 최대 10만개의 정점
// 각 정점 당 3개의 간선 -> 최대 30만개

fun bfs(start: Int, end: Int): Int {
    val visited = BooleanArray(100001)
    var queue: Queue<Int> = LinkedList()

    queue.add(start)
    var distance = 0
    while (queue.isNotEmpty()) {
        val toVisit: Queue<Int> = LinkedList()

        queue.forEach {
            if (it < 0 || it > 100000) {
                return@forEach
            }
            if (it == end) {
                return distance
            }
            if (!visited[it]) {
                visited[it] = true
                val neighbors = mutableListOf(it - 1, it + 1, it * 2)
                toVisit.addAll(neighbors)
            }
        }

        queue = toVisit
        ++distance
    }

    return -1
}

fun main(args: Array<String>) {
    val (n, k) = readln().split(" ").map { it.toInt() }

    val result = bfs(n, k)

    println(result)
}
