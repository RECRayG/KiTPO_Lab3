package com.lab3.gui

import com.lab3.doublyLinkedList.ObjectBuilderFactory
import net.miginfocom.swing.MigLayout
import java.awt.Color
import java.awt.Toolkit
import java.awt.event.ActionEvent
import javax.swing.*

class GUI(actionListener: ListActionListener) : JFrame() {
    private val jList: JList<Any?>
    protected var screenSize = Toolkit.getDefaultToolkit().screenSize
    private val listPanel = JPanel()
    private val rightPanel = JPanel()
    private val addPanel = JPanel()
    private val chooseType = JPanel()
    private val addPanelScroll: JScrollPane
    private val backButton = JButton("< Назад")
    private val comboBox: JComboBox<String?>

    init {
        jList = JList(actionListener.listModel)
        backButton.isEnabled = false
        backButton.foreground = Color(255, 255, 255)
        backButton.background = Color(50, 50, 180)
        addPanel.layout = MigLayout()
        addPanel.background = Color(155, 155, 155)
        rightPanel.background = Color(155, 155, 155)
        listPanel.background = Color(155, 155, 155)
        listPanel.add(JScrollPane(jList))
        rightPanel.layout = MigLayout()
        rightPanel.add(chooseType)
        comboBox = JComboBox(ObjectBuilderFactory.typeNameList.toTypedArray())
        comboBox.foreground = Color(50, 50, 180)
        comboBox.background = Color(255, 255, 255)
        chooseType.layout = MigLayout()
        chooseType.add(comboBox, "w 242!")
        comboBox.addActionListener { actionEvent: ActionEvent ->
            backButton.isEnabled = true
            val source = actionEvent.source as JComboBox<*>
            val selectedItem = source.selectedItem as String
            actionListener.onSelectType(selectedItem)
            rightPanel.add(GUIAction({ text: String -> actionListener.onAdd(text) }, "Добавить элемент"), "wrap, al right")
            rightPanel.add(GUIAction(object : EmptyConsumer {
                override fun accept() {
                    actionListener.onRemove(jList.selectedIndex)
                }
            }, "Удалить элемент"), "wrap, al right")
            rightPanel.add(GUIAction(object : EmptyConsumer {
                override fun accept() {
                    actionListener.onClearList()
                }
            }, "Удалить все элементы"), "wrap, al right")
            rightPanel.add(GUIAction({ text: String -> actionListener.onInsert(text, jList.selectedIndex) }, "Вставить элемент"), "wrap, al right")
            rightPanel.add(GUIAction(object : EmptyConsumer {
                override fun accept() {
                    actionListener.onSort()
                }
            }, "Отсортировать"), "wrap, al right")
            rightPanel.add(GUIAction(object : EmptyConsumer {
                override fun accept() {
                    actionListener.onSave()
                }
            }, "Сохранить"), "split 2, al center")
            rightPanel.add(GUIAction(object : EmptyConsumer {
                override fun accept() {
                    actionListener.onLoad()
                }
            }, "Загрузить"), "wrap")
            rightPanel.remove(chooseType)
            revalidate()
            repaint()
        }
        backButton.addActionListener { actionEvent: ActionEvent? ->
            backButton.isEnabled = false
            actionListener.onClearList()
            rightPanel.removeAll()
            rightPanel.add(chooseType)
            revalidate()
            repaint()
        }
        addPanel.add(backButton, "gaptop 50, gapleft 20, wrap")
        addPanel.add(listPanel, "gapleft 200, wrap")
        addPanel.add(rightPanel, "gapleft 200")
        addPanelScroll = JScrollPane(addPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED)
        addRegistryAddWindow(addPanelScroll, screenSize.getWidth().toInt(), screenSize.getHeight().toInt())
    }

    private fun addRegistryAddWindow(_panel: JScrollPane, width: Int, height: Int) {
        this.setBounds(0, 0, width / 2, (height / 1.2).toInt()) // Устанавливаем оптимальные размеры окна, учитывая размеры экрана
        //this.setExtendedState(this.MAXIMIZED_BOTH); // Растяжение на весь экран
        title = "Двусвязный разомкнутый список" // Заголовок окна
        defaultCloseOperation = EXIT_ON_CLOSE // При нажатии на X ничего не произойдёт
        this.isFocusable = true // Фокус на окне
        this.isResizable = false // Запрет на изменение размеров окна - нет
        //this.setModal(true); // Модальное окно
        setLocationRelativeTo(null) // Расположение окна ровно по центру
        //this.setTitle("ОШИБКА!!!"); // Заголовок окна
        this.layout = MigLayout()
        this.add(_panel, "grow, push")
        this.isVisible = true // Делаем окно видимым
    }
}