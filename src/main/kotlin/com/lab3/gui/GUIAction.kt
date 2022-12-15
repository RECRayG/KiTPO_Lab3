package com.lab3.gui

import java.awt.Color
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.util.function.Consumer
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JTextField

class GUIAction : JPanel {
    constructor(action: EmptyConsumer, buttonText: String?) {
        val button = JButton(buttonText)
        button.foreground = Color(255, 255, 255)
        button.background = Color(50, 50, 180)
        button.addActionListener { e: ActionEvent? -> action.accept() }
        add(button)
    }

    constructor(action: Consumer<String>, buttonText: String?) {
        val textField = JTextField()
        textField.preferredSize = Dimension(100, 25)
        val button = JButton(buttonText)
        button.foreground = Color(255, 255, 255)
        button.background = Color(50, 50, 180)
        button.addActionListener { e: ActionEvent? ->
            action.accept(textField.text)
            textField.text = ""
        }
        add(textField)
        add(button)
    }
}