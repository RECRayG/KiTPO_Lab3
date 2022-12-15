package com.lab3.doublyLinkedList

import com.lab3.doublyLinkedList.build.ObjectBuilder
import org.reflections.Reflections
import java.util.function.Consumer
import java.util.stream.Collectors

object ObjectBuilderFactory {
    private val builders: MutableList<ObjectBuilder> = ArrayList()

    init {
        val reflections = Reflections("com.lab3.doublyLinkedList.build")
        val buildersClasses: Set<Class<out ObjectBuilder>> = reflections.getSubTypesOf(ObjectBuilder::class.java)
        buildersClasses.forEach(Consumer { bc: Class<out ObjectBuilder?>? ->
            try {
                val objectBuilder: ObjectBuilder = bc?.getDeclaredConstructor()?.newInstance()!!
                builders.add(objectBuilder)
            } catch (ignored: Exception) {
                throw RuntimeException("Что-то полшло не так...")
            }
        })
        System.out.printf("%d типа данных было добавлено\n", builders.size)
    }

    val typeNameList: Set<String?>
        get() = builders.stream().map { obj: ObjectBuilder -> obj.typeName() }.collect(Collectors.toSet())

    fun getBuilderByName(name: String?): ObjectBuilder {
        if (name == null) throw NullPointerException()
        for (b in builders) {
            if (name == b.typeName()) return b
        }
        throw IllegalArgumentException()
    }
}