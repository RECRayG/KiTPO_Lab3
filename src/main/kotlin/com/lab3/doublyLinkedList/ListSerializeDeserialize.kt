package com.lab3.doublyLinkedList

import com.lab3.doublyLinkedList.build.ObjectBuilder
import java.io.*
import java.nio.file.Files
import java.nio.file.Paths

object ListSerializeDeserialize {
    @Throws(FileNotFoundException::class)
    fun <T> saveToFile(filename: String, list: DoublyLinkedList<T>?, builder: ObjectBuilder?) {
        var fullPath = ""
        if (!Files.exists(Paths.get("stringData"))) {
            val filePath = File("stringData")
            filePath.mkdir()
        }
        if (!Files.exists(Paths.get("integerData"))) {
            val filePath = File("integerData")
            filePath.mkdir()
        }
        fullPath = if (builder?.typeName() == "String") "stringData\\$filename" else "integerData\\$filename"
        PrintWriter(fullPath).use { writer ->
            writer.println(builder?.typeName())
            list!!.forEach(object : DoWith<Any> {
                override fun doWith(data: Any) {
                    writer.println(builder?.toString(data))
                }
            })
        //{ el: DoWith<Any>? -> writer.println(builder?.toString(el)) }
        }
    }

    @Throws(Exception::class)
    fun <T> loadFromFile(filename: String, builder: ObjectBuilder?, list: LinkedList<Any>): LinkedList<Any> {
        var fullPath = ""
        if (builder?.typeName() == "String" && Files.exists(Paths.get("stringData\\$filename"))) fullPath = "stringData\\$filename" else {
            if (builder?.typeName() != "Integer") throw Exception("Загружаемый файл отсутствует!")
        }
        if (builder?.typeName() == "Integer" && Files.exists(Paths.get("integerData\\$filename"))) fullPath = "integerData\\$filename" else {
            if (builder.typeName() != "String") throw Exception("Загружаемый файл отсутствует!")
        }
        BufferedReader(FileReader(fullPath)).use { br ->
            var line: String?
            line = br.readLine()
            if (builder.typeName() != line) {
                throw Exception("Неверная структура файла!")
            }
            while (br.readLine().also { line = it } != null) {
                list.add(builder.createFromString(line!!))
            }

            return list
        }
    }
}