import java.util.*
import kotlin.math.max

// https://www.acmicpc.net/problem/1374 g5

fun main(args: Array<String>) {
    val nCourses = readln().toInt()

    // 사실 정렬로 구현해도 됩니다. 시간 복잡도는 같습니다.
    val priorityQueue = PriorityQueue(
        nCourses * 2,
        compareBy<Pair<Int, Boolean>> { it.first }.thenBy { it.second }
    )
    repeat(nCourses) {
        priorityQueue.addAll(readln()
            .split(" ")
            .map { it.toInt() }
            .let { listOf(it[1] to true, it[2] to false) })
    }

    var currentRooms = 0
    var result = 0

    while (priorityQueue.isNotEmpty()) {
        val (_, isStartTime) = priorityQueue.poll()
        if (isStartTime) {
            currentRooms++
            result = max(result, currentRooms)
        } else {
            currentRooms--
        }
    }

    println(result)
}