package com.lab3.doublyLinkedList.build

import com.lab3.doublyLinkedList.Comparator
import java.util.*

class IntegerObjectBuilder : ObjectBuilder {
    override fun typeName(): String {
        return "Integer"
    }

    override fun create(): Int {
        return Random().nextInt(100)
    }

    override fun createFromString(s: String): Int {
        return s.toInt()
    }

    override fun toString(`object`: Any): String? {
        return `object`.toString()
    }

    override fun compare(o1: Any, o2: Any): Int {
        return (o1 as Int - o2 as Int)
    }

    override val typeComparator: Comparator<Any>
        get() = Comparator { o1: Any, o2: Any -> compare(o1, o2) }
}