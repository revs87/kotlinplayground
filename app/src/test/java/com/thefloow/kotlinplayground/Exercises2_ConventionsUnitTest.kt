package com.thefloow.kotlinplayground

import com.thefloow.kotlinplayground.exercises.Invokable
import com.thefloow.kotlinplayground.exercises.invokeTwice
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class Exercises2_ConventionsUnitTest {

    /**
     * #7
     * https://play.kotlinlang.org/koans/Conventions/Invoke/Task.kt
     * */
    @Test
    fun invokable() {
        val invokable = Invokable()
        invokeTwice(invokable)
    }

}
