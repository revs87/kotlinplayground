package com.thefloow.kotlinplayground.exercises

import java.util.*


/**
 * Created by Rui Vieira on 13/02/2019.
 * The Floow Ltd
 * rui.vieira@thefloow.com
 */

class Exercises2_Conventions {
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
//operator fun MyDate.rangeTo(other: MyDate) = DateRange2(this, other)

class DateRange2(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>

fun checkInRange(date: MyDate, first: MyDate, last: MyDate): Boolean {
    return date in first..last
}

/**
 * #4
 * https://play.kotlinlang.org/koans/Conventions/For%20loop/Task.kt
 * */
operator fun MyDate.rangeTo(other: MyDate) = DateRange3(this, other)

class DateRange3(val start: MyDate, val end: MyDate) : Iterable<MyDate> {
    override operator fun iterator(): Iterator<MyDate> = DateIteration(this)
}

class DateIteration(val dateRange: DateRange3) : Iterator<MyDate> {
    var current = dateRange.start

    override fun hasNext(): Boolean = when {
        current <= dateRange.end -> true
        else -> false
    }

    override fun next(): MyDate {
        val result = current
        current = current.nextDay()
        return result
    }
}

fun iterateOverDateRange(firstDate: MyDate, secondDate: MyDate, handler: (MyDate) -> Unit) {
    for (date in firstDate..secondDate) {
        handler(date)
    }
}

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

/**
 * #5
 * https://play.kotlinlang.org/koans/Conventions/Operators%20overloading/Task.kt
 * */
operator fun MyDate.plus(timeInterval: TimeInterval): MyDate = addTimeIntervals(timeInterval, 1)

fun task1(today: MyDate): MyDate {
    return today + TimeInterval.YEAR + TimeInterval.WEEK
}

data class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Int)

operator fun TimeInterval.times(number: Int) = RepeatedTimeInterval(this, number)

operator fun MyDate.plus(timeIntervals: RepeatedTimeInterval): MyDate =
    addTimeIntervals(timeIntervals.timeInterval, timeIntervals.number)

fun task2(today: MyDate): MyDate {
    return today + TimeInterval.YEAR * 2 + TimeInterval.WEEK * 3 + TimeInterval.DAY * 5
}

/**
 * #6
 * https://play.kotlinlang.org/koans/Conventions/Destructuring%20declarations/Task.kt
 * */
data class MyDate2(val year: Int, val month: Int, val dayOfMonth: Int)

fun isLeapDay(date: MyDate2): Boolean {

    val (year, month, dayOfMonth) = date

    // 29 February of a leap year
    return year % 4 == 0 && month == 2 && dayOfMonth == 29
}

/**
 * #7
 * https://play.kotlinlang.org/koans/Conventions/Invoke/Task.kt
 * */
class Invokable {
    var numberOfInvocations: Int = 0
        private set

    operator fun invoke(): Invokable {
        numberOfInvocations++
        return this
    }
}

fun invokeTwice(invokable: Invokable) = invokable()()
