package com.lab3.gui

import javax.swing.DefaultListModel

interface ListActionListener {
    fun onAdd(text: String)
    fun onInsert(text: String, index: Int)
    fun onRemove(index: Int)
    fun onSort()
    fun onSave()
    fun onLoad()
    val listModel: DefaultListModel<Any>
    fun onSelectType(type: String?)
    fun onClearList()
}