// https://www.acmicpc.net/problem/1918 g2

// 새 연산자의 우선순위가
// 더 큼 -> push to stack
// 작거나 같음 -> 우선순위가 더 작은 게 나올 때까지 pop

import java.util.*

val stack = Stack<Char>()

var parenthesis = 0

val operatorStack = Stack<Pair<Char, Int>>()

fun popOperatorStack(until: Int = 0) {
    while (operatorStack.isNotEmpty() && operatorStack.peek().second >= until) {
        stack.push(operatorStack.pop().first)
    }
}

fun getCurrentPriority(operator: Char): Int {
    return when (operator) {
        '+' -> 1
        '-' -> 1
        '*' -> 2
        '/' -> 2
        else -> 0
    } + parenthesis * 10
}

fun main(args: Array<String>) {
    val chars = readln()
    chars.forEach {
        if (it.isLetter()) {
            stack.push(it)
            return@forEach
        }

        when (it) {
            '(' -> ++parenthesis
            ')' -> {
                --parenthesis
            }

            else -> {
                val currentPriority = getCurrentPriority(it)
                popOperatorStack(until = currentPriority)
                operatorStack.push(it to currentPriority)
            }
        }
    }

    popOperatorStack()

    println(stack.joinToString("") { it.toString() })
}
