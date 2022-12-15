package com.lab3.gui

import com.lab3.doublyLinkedList.*
import java.io.FileNotFoundException

class ListActionListenerOverride : AbstractListActionListener() {
    protected var items: LinkedList<Any> = LinkedList<Any>()
    override fun onAdd(text: String) {
        if (text.isEmpty()) return
        val data = builder!!.createFromString(text)
        items!!.add(data)
        listModel.addElement(data)
    }

    override fun onInsert(text: String, index: Int) {
        if (text.isEmpty()) return
        val data = builder!!.createFromString(text)
        items!!.add(data, index)
        listModel.add(index, data)
    }

    override fun onRemove(index: Int) {
        items!!.remove(index)
        listModel.remove(index)
    }

    override fun onSort() {
        builder?.typeComparator?.let { items!!.sort(it) }
        listModel.clear()
        items!!.forEach(object : DoWith<Any> {
            override fun doWith(data: Any) {
                listModel.addElement(data)
            }
        })
    }

    override fun onSave() {
        try {
            ListSerializeDeserialize.saveToFile(filename, items, builder)
        } catch (e: FileNotFoundException) {
            System.err.println("Нельзя записать список в файл!")
            e.printStackTrace()
        }
    }

    override fun onLoad() {
        try {
            items = LinkedList<Any>()?.let { ListSerializeDeserialize.loadFromFile<Any?>(filename, builder, it) }!!
            listModel.clear()
            items!!.forEach(object : DoWith<Any> {
                override fun doWith(data: Any) {
                    listModel.addElement(data)
                }
            })
        } catch (e: Exception) {
            System.err.println("Нельзя прочитать список из файла!")
            e.printStackTrace()
        }
    }

    override fun onClearList() {
        items!!.removeAll()
        listModel.removeAllElements()
    }
}