package com.lab3.doublyLinkedList


interface DoublyLinkedList<T> {
    fun add(data: Any)
    operator fun get(index: Int): T
    fun remove(index: Int)
    fun removeAll()
    fun size(): Int
    fun add(data: T, index: Int)
    fun forEach(a: DoWith<Any>?)
    fun sort(comparator: Comparator<Any>)
}