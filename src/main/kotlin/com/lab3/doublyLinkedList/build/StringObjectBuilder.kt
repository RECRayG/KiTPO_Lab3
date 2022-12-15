package com.lab3.doublyLinkedList.build

import com.lab3.doublyLinkedList.Comparator
import java.util.*

class StringObjectBuilder : ObjectBuilder {
    override fun typeName(): String {
        return "String"
    }

    override fun create(): String {
        return string
    }

    override fun createFromString(s: String): String {
        return s
    }

    override fun toString(`object`: Any): String? {
        return `object`.toString()
    }

    override fun compare(o1: Any, o2: Any): Int {
        return o1.toString().compareTo(o2.toString())
    }

    override val typeComparator: Comparator<Any>
        get() = Comparator { o1: Any, o2: Any -> compare(o1, o2) }

    companion object {
        private val string: String
            private get() {
                val leftLimit = 97
                val rightLimit = 122
                val targetStringLength = 4
                val random = Random()
                val buffer = StringBuilder(targetStringLength)
                for (i in 0 until targetStringLength) {
                    val randomLimitedInt = leftLimit + (random.nextFloat() * (rightLimit - leftLimit + 1)).toInt()
                    buffer.append(randomLimitedInt.toChar())
                }
                return buffer.toString()
            }
    }
}