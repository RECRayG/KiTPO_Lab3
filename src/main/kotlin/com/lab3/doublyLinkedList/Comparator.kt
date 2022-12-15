package com.lab3.doublyLinkedList

interface Comparator<Any> {
    fun compare(o1: Any, o2: Any): Int //{
//        return o1.toString().compareTo(o2.toString())
    //}
}

fun Comparator(block: (Any, Any) -> Int): Comparator<Any> = object : Comparator<Any> { override fun compare(o1: Any, o2: Any): Int = block(o1, o2) }


//class Comparator<Any> (o1: Any, o2: Any) : Comparable<Any> {
//    val param1: Any
//    init {
//        param1 = o1
//    }
//
//    val param2: Any
//    init {
//        param2 = o2
//    }
//
//    fun compare(o1: Any, o2: Any): Int {
//        return o1.toString().compareTo(o2.toString())
//    }
//
//     override fun compareTo(other: Any): Int {
//         return this.compareTo(other)
//     }
// }