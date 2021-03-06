package com.thefloow.kotlinplayground

import com.thefloow.kotlinplayground.exercises.getList2
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class Exercises1_IntroductionUnitTest {

    /**
     * #5
     * https://play.kotlinlang.org/koans/Introduction/Strings/Task.kt
     * */
    @Test
    fun strings() {
        val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"
        val regex = """\d{2} ${month} \d{4}""".toRegex()

        assertTrue(regex.matches("01 JAN 2019"))
        assertFalse(regex.matches("1 JAN 2019"))
        assertFalse(regex.matches("01 JA 2019"))
        assertFalse(regex.matches("01 02 2019"))
        assertFalse(regex.matches("01 02 2019"))
        assertFalse(regex.matches("01 JAN 201"))
    }

    /**
     * #11
     * https://play.kotlinlang.org/koans/Introduction/SAM%20conversions/Task.kt
     * */
    @Test
    fun sams() {
        println("output: " + getList2())
    }
}
