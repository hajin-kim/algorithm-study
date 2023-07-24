// https://www.acmicpc.net/problem/13335

import java.util.*

// time: O(nTrucks * bridgeLength) if trucks are all the same weight with maxWeight
// 다리의 무게가 꽉 찼을 때 불필요한 반복을 생략하면 시간 단축 가능
// space: O(nTrucks + bridgeLength)
fun main(args: Array<String>) {
    val (nTrucks, bridgeLength, maxWeight) = readln().split(" ").map { it.toInt() }
    val readyTrucks: Queue<Int> = readln().split(" ").map { it.toInt() }.toCollection(LinkedList())
    val bridge: Queue<Int> = IntArray(bridgeLength).apply { fill(0) }.toCollection(LinkedList())

    var time = 0
    var currentWeight = 0
    while (readyTrucks.isNotEmpty()) {
        currentWeight -= bridge.poll()

        if (currentWeight + readyTrucks.peek() <= maxWeight) {
            val truck = readyTrucks.poll()
            bridge.add(truck)
            currentWeight += truck
        } else {
            bridge.add(0)
        }

        ++time
    }

    println(time + bridgeLength)
}
