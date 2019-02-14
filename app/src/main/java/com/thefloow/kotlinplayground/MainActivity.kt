package com.thefloow.kotlinplayground

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.thefloow.kotlinplayground.utils.*
import com.thefloow.kotlinplayground.exercises.Exercises1_Conventions
import com.thefloow.kotlinplayground.exercises.Exercises1_Introduction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private lateinit var textView: TextView
    private lateinit var username: EditText
    private lateinit var button: Button


    private fun init() {
        val textView: TextView = findViewById(R.id.planet)
        val username: EditText = findViewById(R.id.username)
        val button: Button = findViewById(R.id.button)

        var text1 = mightBeNull("")
        text1 = text1 ?: "Ok2"

        var text2 = neverBeNull(null)
        text2 = text2

        textView.text = text2 + planet(3, "-Earth")

        val validation = ValidationText()
        validation.isAtLeastFour(username.text?.toString())

        val gc = ExtensionFunctionClass()
        gc.inTransaction1 {
            gc.doMainStuff1()
        }
        gc.inTransaction2 {
            it.doMainStuff2()
        }
        gc.inTransaction3 {
            doMainStuff3()
        }
        gc.inTransaction4 {
            doMainStuff4()
        }

        val ex1 = Exercises1_Introduction()
        ex1.run()
        val ex2 = Exercises1_Conventions()
        ex2.run()

        val notification = notification(applicationContext) {
            setContentTitle("ContentTitle")
            setContentText("ContentText")
        }
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(applicationContext, "Hello", Toast.LENGTH_SHORT).show()
                notificationManager.notify(0, notification)
            }
        })
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

    inline fun notification(context: Context, func: NotificationCompat.Builder.() -> Unit): Notification {
        val builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(context, "MyChannelId")
        } else {
            NotificationCompat.Builder(context)
        }
        builder.setSmallIcon(R.mipmap.ic_launcher_round)
        builder.func()
        return builder.build()
    }
}


