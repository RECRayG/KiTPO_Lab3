package com.lab3.doublyLinkedList

class LinkedList<T : Any> : DoublyLinkedList<Any> {
    private var head: Node? = null
    private var tail: Node? = null
    private var length = 0
    override fun add(data: Any) {
        if (head == null) {
            head = Node(data)
            tail = head
            length++
            return
        }
        val newTail: Node = Node(data)
        newTail.prev = tail
        tail?.next = newTail
        tail = newTail
        length++
    }

    override fun get(index: Int): Any {
        return getNode(index)!!.data
    }

    override fun remove(index: Int) {
        val tmp: Node? = getNode(index)
        if (tmp === head) {
            tmp?.prev = null
            head = tmp?.next
        } else {
            if (tmp?.prev != null) {
                tmp?.prev?.next = tmp.next
                tmp.prev = null
            }
        }
        if (tmp === tail) {
            tmp?.next = null
            tail = tmp?.prev
        } else {
            if (tmp?.next != null) {
                tmp?.next?.prev = tmp.prev
                tmp?.next = null
            }
        }
        tmp?.next = null
        tmp?.prev = null

        length--
    }

    override fun size(): Int {
        return length
    }

    override fun add(data: Any, index: Int) {
        val tmp: Node? = getNode(index)
        val newNode: Node = Node(data)
        if (tmp !== head) {
            tmp?.prev?.next = newNode
            newNode.prev = tmp?.prev
        } else {
            head = newNode
        }
        newNode.next = tmp
        tmp?.prev = newNode
        length++
    }

    override fun removeAll() {
        if (head != null) {
            var tmp: Node? = getNode(0)
            while (tmp?.next != null) {
                remove(0)
                tmp = getNode(0)
            }
            remove(0)
        }
    }

    override fun forEach(a: DoWith<Any>?) {
        var tmp: Node? = head
        for (i in 0 until length) {
            a?.doWith(tmp!!.data)
            tmp = tmp?.next
        }
    }

    override fun sort(comparator: Comparator<Any>) {
        head = mergeSort(head, comparator)
    }

    private fun mergeSort(h: Node?, comparator: Comparator<Any>): Node? {
        if (h == null || h.next == null) {
            return h
        }
        val middle: Node? = getMiddle(h)
        val middleNext: Node = middle?.next!!
        middle?.next = null
        val left: Node? = mergeSort(h, comparator)
        val right: Node? = mergeSort(middleNext, comparator)
        return merge(left, right, comparator)
    }

    private fun merge(head11: Node?, head22: Node?, comparator: Comparator<Any>): Node {
        var left: Node? = head11
        var right: Node? = head22
        val merged: Node? = left?.let { Node(it) }
        var temp: Node? = merged
        while (left != null && right != null) {
            var sa: Int = comparator?.compare(left.data, right.data)!!
            //if (comparator?.compare(left.data, right.data).toString()[0].equals("-")) {
            if (sa < 0) {
                temp?.next = left
                left?.prev = temp
                left = left?.next
            } else {
                temp?.next = right
                right?.prev = temp
                right = right?.next
            }
            temp = temp?.next
        }
        while (left != null) {
            temp?.next = left
            left?.prev = temp
            left = left?.next
            temp = temp?.next
        }
        while (right != null) {
            temp?.next = right
            right?.prev = temp
            right = right?.next
            temp = temp?.next
            tail = temp
        }
        return merged?.next!!
    }

    private fun getMiddle(h: Node?): Node? {
        if (h == null) return null
        var fast: Node = h.next!!
        var slow: Node = h
        while (fast.next != null) {
            fast = fast.next!!
            if (fast.next != null) {
                slow = slow.next!!
                fast = fast.next!!
            }
        }
        return slow
    }

    private fun getNode(index: Int): Node? {
        if (index < 0 || index >= length) throw IndexOutOfBoundsException()
        var tmp: Node? = head
        for (i in 0 until index) {
            tmp = tmp?.next
        }
        return tmp
    }

    private inner class Node(var data: Any) {
        var next: Node?
        var prev: Node? = null

        init {
            next = prev
        }
    }
}

//private operator fun Any?.compareTo(i: Int): Int {
//    var few: String = ""
//    return this.compareTo(i)
//}
