package com.thefloow.kotlinplayground.utils.exercises

import java.util.*


/**
 * Created by Rui Vieira on 13/02/2019.
 * The Floow Ltd
 * rui.vieira@thefloow.com
 */

class Exercises1_Conventions {
    fun run() {
    }

}

/**
 * #1
 * https://play.kotlinlang.org/koans/Conventions/Comparison/Task.kt
 * */
data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate) = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}

fun compare(date1: MyDate, date2: MyDate) = date1 < date2


/**
 * #2
 * https://play.kotlinlang.org/koans/Conventions/In%20range/Task.kt
 * */
class DateRange1(val start: MyDate, val endInclusive: MyDate) {
    operator fun contains(item: MyDate) = when {
        start < item && item <= endInclusive -> true
        else -> false
    }
}

fun checkInRange1(date: MyDate, first: MyDate, last: MyDate): Boolean {
    return date in DateRange1(first, last)
}

/**
 * #3
 * https://play.kotlinlang.org/koans/Conventions/Range%20to/Task.kt
 * */
operator fun MyDate.rangeTo(other: MyDate) = DateRange2(this, other)

class DateRange2(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>

fun checkInRange(date: MyDate, first: MyDate, last: MyDate): Boolean {
    return date in first..last
}

/**
 * #4
 * https://play.kotlinlang.org/koans/Conventions/For%20loop/Task.kt
 * */
//TODO
//class DateRange(val start: MyDate, val end: MyDate) : Iterable<MyDate> {
//    override fun iterator(): Iterator<MyDate> = {forEach { start }}
//}
//
//fun iterateOverDateRange(firstDate: MyDate, secondDate: MyDate, handler: (MyDate) -> Unit) {
//    for (date in firstDate..secondDate) {
//        handler(date)
//    }
//}

fun MyDate.nextDay() = addTimeIntervals(TimeInterval.DAY, 1)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

fun MyDate.addTimeIntervals(timeInterval: TimeInterval, number: Int): MyDate {
    val c = Calendar.getInstance()
    c.set(year, month, dayOfMonth)
    when (timeInterval) {
        TimeInterval.DAY -> c.add(Calendar.DAY_OF_MONTH, number)
        TimeInterval.WEEK -> c.add(Calendar.WEEK_OF_MONTH, number)
        TimeInterval.YEAR -> c.add(Calendar.YEAR, number)
    }
    return MyDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))

}