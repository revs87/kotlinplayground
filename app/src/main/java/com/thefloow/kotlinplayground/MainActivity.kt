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
    val fourDigits: (String) -> Boolean = { it.matches(Regex("\\d{4}")) }
    val validCreditCard: (String) -> Boolean = { Luhn.isValid(it) }

    private lateinit var textView: TextView
    private lateinit var username: EditText

    private fun init() {

        var text1 = mightBeNull("")
        text1 = text1 ?: "Ok2"

        var text2 = neverBeNull(null)
        text2 = text2

        var textView: TextView = findViewById(R.id.planet)
        textView.text = text2 + planet(3, "-Earth")

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
        fun acquire(func: (T) -> Unit) {
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

    object Luhn {
        fun isValid(input: String): Boolean {
            val sanitizedInput = input.replace(" ", "")

            return when {
                valid(sanitizedInput) -> checksum(sanitizedInput) % 10 == 0
                else -> false
            }
        }

        private fun valid(input: String) = input.all(Char::isDigit) && input.length > 1

        private fun checksum(input: String) = addends(input).sum()

        private fun addends(input: String) = input.digits().mapIndexed { i, j ->
            when {
                (input.length - i + 1) % 2 == 0 -> j
                j >= 5 -> j * 2 - 9
                else -> j * 2
            }
        }

        private fun String.digits() = this.map(Character::getNumericValue)
    }
}

