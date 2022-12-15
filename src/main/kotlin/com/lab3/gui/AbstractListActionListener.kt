package com.lab3.gui

import com.lab3.doublyLinkedList.ObjectBuilderFactory
import com.lab3.doublyLinkedList.build.ObjectBuilder
import javax.swing.DefaultListModel

abstract class AbstractListActionListener : ListActionListener {
    protected val filename = "listData.dat"
    override val listModel = DefaultListModel<Any>()
    protected var builder: ObjectBuilder? = null
    override fun onSelectType(type: String?) {
        try {
            builder = ObjectBuilderFactory.getBuilderByName(type)
        } catch (ignored: Exception) {
        }
    }
}