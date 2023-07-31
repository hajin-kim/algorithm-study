// https://www.acmicpc.net/problem/1991 s1

class Node<T>(
    var name: T,
    var left: T?,
    var right: T?,
) {
    fun preOrder(nodes: Map<T, Node<T>>) {
        print(name)
        nodes[left]?.preOrder(nodes)
        nodes[right]?.preOrder(nodes)
    }

    fun inOrder(nodes: Map<T, Node<T>>) {
        nodes[left]?.inOrder(nodes)
        print(name)
        nodes[right]?.inOrder(nodes)
    }

    fun postOrder(nodes: Map<T, Node<T>>) {
        nodes[left]?.postOrder(nodes)
        nodes[right]?.postOrder(nodes)
        print(name)
    }
}

fun main(args: Array<String>) {
    val n = readln().toInt()

    val nodes = (1..n)
        .map {
            val (nodeString, left, right) = readln().split(" ")
            Node(nodeString, left, right)
        }
        .associateBy { it.name }

    val rootNode = nodes["A"]!!
    rootNode.preOrder(nodes)
    println()
    rootNode.inOrder(nodes)
    println()
    rootNode.postOrder(nodes)
    println()
}