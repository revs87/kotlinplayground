package com.thefloow.kotlinplayground

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }


    val length: (String) -> Int = { it.length }
    val notEmpty: (String) -> Boolean = { !it.isEmpty() }
    val atLeastFour: (String) -> Boolean = { it.length >= 4 }

    private lateinit var textView: TextView
    private lateinit var username: EditText

    private fun init() {

        var text1 = mightBeNull("")
        text1 = text1 ?: "Ok2"

        var text2 = neverBeNull(null)
        text2 = text2 ?: "Ok3"

        var textView: TextView = findViewById(R.id.planet)
        textView.setText(text2 + planet(3, "-Earth"))

        username.validateWith { it.length() > 4 }
    }

    fun mightBeNull(text: String): String? {
        if (text.isEmpty())
            return null
        else
            return "Ok"
    }

    fun neverBeNull(text: String?): String {
        return text + ""
    }

    var planet: (Int, String) -> String = { x, y -> x.toString() + y }

    fun <T> List<T>.filter(predicate: (T) -> Boolean): List<T> {
        val newList = ArrayList<T>()
        for (item in this) {
            if (predicate(item)) {
                newList.add(item)
            }
        }
        return newList
    }

    val names = listOf("Rui", "Wale", "John", "Alex")
    val johns = names.filter { it == "John" }

    data class Lock<T>(private val obj: T) {
        public fun acquire(func: (T) -> Unit) {
            synchronized(func) {
                func(obj)
            }
        }
    }

    fun <T> T.validateWith(predicate: (T) -> Boolean): Boolean {
        val item = this
        if (predicate(item)) {
            return true
        }
        return false
    }

}