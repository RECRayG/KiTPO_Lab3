package com.lab3.doublyLinkedList.build

import com.lab3.doublyLinkedList.Comparator

interface Build<Any> {
    fun typeName(): String
    fun create(): Any
    fun compare(o1: Any, o2: Any): Int
    val typeComparator: Comparator<Any>
    fun createFromString(s: String): Any
    fun toString(`object`: Any): String?
}