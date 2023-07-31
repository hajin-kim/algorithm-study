import java.util.*

// https://www.acmicpc.net/problem/1417

// disclaimer: 어떠한 정치적 의도도 없습니다.

fun main(args: Array<String>) {
    val n = readln().toInt()

    // base case
    if (n == 1) {
        println(0)
        return
    }

    // 지지자의 득표수를 따로 변수로 관리합니다.
    var firstVotes = readln().toInt()
    // 나머지 후보의 득표수를 최댓값 우선순위 큐로 관리합니다.
    val otherVotes = (2..n).map { readln().toInt() }
    val otherVotesPriorityQueue: PriorityQueue<Int> = PriorityQueue<Int>(otherVotes.size, Collections.reverseOrder())
    otherVotesPriorityQueue.addAll(otherVotes)

    // 이 문제의 제약조건에서는 잘 작동하는 솔루션입니다.
    var answer = 0
    // 우선순위 큐의 최댓값이 지지자의 득표수보다 크다면, 해당 상대 후보의 지지자 1명을 매수합니다.
    while (firstVotes <= otherVotesPriorityQueue.peek()) {
        ++firstVotes
        ++answer
        // 상대 후보의 득표수를 1 감소시키고 큐에 다시 넣습니다.
        otherVotesPriorityQueue.add(otherVotesPriorityQueue.poll() - 1)
    }

    println(answer)

    // 만약 N과 득표수의 제약조건이 훨씬 더 엄격했었다면 시간초과가 발생할 수도 있습니다.
    // 그런 경우 1개씩 매수하는 것을 시뮬레이션하는 것보다, `(우선순위 큐의 최댓값과의 표 차이 + 1) // 2`표씩 매수하는 것이 더 적절합니다.
    var secondAnswer = 0
    while (firstVotes <= otherVotesPriorityQueue.peek()) {
        val diff = (otherVotesPriorityQueue.peek() - firstVotes) / 2
        firstVotes += diff
        secondAnswer += diff
        otherVotesPriorityQueue.add(otherVotesPriorityQueue.poll() - diff)
    }

    assert(answer == secondAnswer)
}
