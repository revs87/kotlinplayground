package com.thefloow.kotlinplayground.exercises


/**
 * Created by Rui Vieira on 13/02/2019.
 * The Floow Ltd
 * rui.vieira@thefloow.com
 */

class Exercises3_Conventions {
    fun run() {
    }

}

/**
 * #1
 * https://play.kotlinlang.org/koans/Collections/Introduction/Task.kt
 * */
data class Shop(val name: String, val customers: List<Customer>)

data class Customer(val name: String, val city: City, val orders: List<Order>) {
    override fun toString(): String = "$name from ${city.name}"
}

data class City(val name: String) {
    override fun toString(): String = name
}

data class Order(val products: List<Product>, val isDelivered: Boolean)

data class Product(val name: String) {
    override fun toString(): String = name
}

fun Shop.getSetOfCustomers(): Set<Customer> = customers.toSet()

/**
 * #2
 * https://play.kotlinlang.org/koans/Collections/Filter%20map/Task.kt
 * */
// Return the set of cities the customers are from
fun Shop.getCitiesCustomersAreFrom(): Set<City> = TODO()

// Return a list of the customers who live in the given city
fun Shop.getCustomersFrom(city: City): List<Customer> = TODO()
