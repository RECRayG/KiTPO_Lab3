package com.lab3

import com.lab3.doublyLinkedList.*
import com.lab3.doublyLinkedList.build.ObjectBuilder
import com.lab3.gui.GUI
import com.lab3.gui.ListActionListener
import com.lab3.gui.ListActionListenerOverride

object Main {
    private fun testIt(builder: ObjectBuilder?) {
        var i = 1
        while (i < 2000) {
            val n = i * 1000
            println("N = $n")
            val list = LinkedList<Any>()
            for (j in 0 until n) list.add(builder!!.create())
            val start = System.nanoTime()
            try {
                builder?.typeComparator?.let { list.sort(it) }
            } catch (ignored: StackOverflowError) {
                System.err.println("Ошибка сортировки!")
                return
            }
            val end = System.nanoTime()
            println("Время сортировки: " + (end - start) * 1.0 / 1000000 + " мс")
            i *= 2
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val builder = ObjectBuilderFactory.getBuilderByName("Integer")
        testIt(builder)
//        val list = LinkedList<Any>()
//        list.add(builder!!.create())
//        list.add(builder.create())
//        list.add(builder.create())
//        list.add(builder.create())
//        list.add(builder.create())
//        println("\nИнициализация двусвязного разомкнутого списка")
//        list.forEach { x: Any? -> println(x) }
//        list.sort(builder.typeComparator)
//        println("\nСортировка")
//        list.forEach { x: Any? -> println(x) }
//        list.remove(1)
//        println("\nУдаление элемента с индексом 1")
//        list.forEach { x: Any? -> println(x) }
//        list.remove(0)
//        println("\nУдаление элемента с индексом 0")
//        list.forEach { x: Any? -> println(x) }
//        list.remove(2)
//        println("\nУдаление элемента с индексом 2")
//        list.forEach { x: Any? -> println(x) }
//        list.add(builder.create(), 1)
//        println("\nДобавление элемента с индексом 1")
//        list.forEach { x: Any? -> println(x) }
//        list.add(builder.create(), 0)
//        println("\nДобавление элемента с индексом 0")
//        list.forEach { x: Any? -> println(x) }
//        list.add(builder.create(), 2)
//        println("\nДобавление элемента с индексом 2")
//        list.forEach { x: Any? -> println(x) }
        val listActionListener: ListActionListener = ListActionListenerOverride()
        GUI(listActionListener)
    }
}