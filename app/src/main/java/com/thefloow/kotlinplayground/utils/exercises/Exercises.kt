package com.thefloow.kotlinplayground.utils.exercises


/**
 * Created by Rui Vieira on 12/02/2019.
 * The Floow Ltd
 * rui.vieira@thefloow.com
 */

// https://play.kotlinlang.org/koans/Introduction/Hello,%20world!/Task.kt
class Exercises {

    /**
     * #2
     * https://play.kotlinlang.org/koans/Introduction/Named%20arguments/Task.kt
     * */
    fun joinOptions(options: Collection<String>) = options.joinToString(prefix = "[", postfix = "]")


    /**
     * #3
     * https://play.kotlinlang.org/koans/Introduction/Default%20arguments/Task.kt
     * */
    fun foo(name: String, number: Int = 1, toUpperCase: Boolean = false) {}

    fun useFoo() = listOf(
        foo("a"),
        foo("b", number = 1),
        foo("c", toUpperCase = true),
        foo(name = "d", number = 2, toUpperCase = true)
    )

    /**
     * #4
     * https://play.kotlinlang.org/koans/Introduction/Lambdas/Task.kt
     * */
    fun containsEven(collection: Collection<Int>): Boolean = collection.any {
        collection.contains(it) && it % 2 == 0
    }

    fun run() {


    }

}

/**
 * #6
 * https://play.kotlinlang.org/koans/Introduction/Data%20classes/Task.kt
 * */
data class Person(val name: String, val age: Int)

fun getPeople(): List<Person> {
    return listOf(Person("Alice", 29), Person("Bob", 31))
}

/**
 * #7
 * https://play.kotlinlang.org/koans/Introduction/Nullable%20types/Task.kt
 * */
class Client(val personalInfo: PersonalInfo?)

class PersonalInfo(val email: String?)
interface Mailer {
    fun sendMessage(email: String, message: String)
}

fun sendMessageToClient(client: Client?, message: String?, mailer: Mailer) {
    val email = client?.personalInfo?.email
    if (email != null && message != null) {
        mailer.sendMessage(email, message)
    }
}

/**
 * #8
 * https://play.kotlinlang.org/koans/Introduction/Smart%20casts/Task.kt
 * */
interface Expr

class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(expr: Expr): Int =
    when (expr) {
        is Num -> expr.value
        is Sum -> eval(expr.left) + eval(expr.right)
        else -> throw IllegalArgumentException("Unknown expression")
    }

/**
 * #9
 * https://play.kotlinlang.org/koans/Introduction/Extension%20functions/Task.kt
 * */
data class RationalNumber(val numerator: Int, val denominator: Int)

fun Int.r(): RationalNumber = RationalNumber(this, 1)
fun Pair<Int, Int>.r(): RationalNumber = RationalNumber(this.first, this.second)

