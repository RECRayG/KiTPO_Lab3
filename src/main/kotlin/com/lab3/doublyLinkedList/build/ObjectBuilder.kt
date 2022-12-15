package com.lab3.doublyLinkedList.build

import com.lab3.doublyLinkedList.Comparator

interface ObjectBuilder : Build<Any> {
    override fun typeName(): String
    override fun create(): Any
    override fun compare(o1: Any, o2: Any): Int
    override val typeComparator: Comparator<Any>
    override fun createFromString(s: String): Any
    override fun toString(`object`: Any): String?
}